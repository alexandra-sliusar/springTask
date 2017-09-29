package ua.rd.ioc;

import ua.rd.services.TweetService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.stream.Stream;

public class ApplicationContext implements Context {

    private List<BeanDefinition> beanDefinitions;
    private Map<String, Object> beans = new HashMap<>();

    public ApplicationContext(Config config) {
        beanDefinitions = config.beanDefinitions();
        initContext(beanDefinitions);
    }

    private void initContext(List<BeanDefinition> beanDefinitions) {
        beanDefinitions.forEach(bd -> getBean(bd.getBeanName()));
    }

    public ApplicationContext() {
        beanDefinitions = Arrays.asList(Config.EMPTY_BEANDEFINITION);//new BeanDefinition[0];
    }

    public Object getBean(String beanName) {
        //BeanDefinition beanDefinition = getBeanDefinitionByName(beanName);

        return Optional
                .ofNullable(beans.get(beanName))
                .orElseGet(() ->
                        createBeanByBeanDefinition(getBeanDefinitionByName(beanName))
                );
    }

    private Object createBeanByBeanDefinition(BeanDefinition beanDefinition) {
        String beanName = beanDefinition.getBeanName();
        Object bean = createNewBean(beanDefinition);
        if (!beanDefinition.isPrototype()) {
            beans.put(beanName, bean);
        }
        return bean;
    }

    private Object createNewBean(BeanDefinition beanDefinition) {
        BeanBuilder beanBuilder = new BeanBuilder(beanDefinition);
        beanBuilder.createNewBeanInstance();
        beanBuilder.callPostConstructAnnotatedMethod();
        beanBuilder.callInitMethod();
        beanBuilder.createBenchmarkProxy();
        if (beanDefinition.getBeanName().equals("tweetService")) {
            beanBuilder.createTweetServiceProxy();
        }

        Object bean = beanBuilder.build();
        return bean;
    }


    private BeanDefinition getBeanDefinitionByName(String beanName) {
        return beanDefinitions.stream()
                .filter(bd -> Objects.equals(bd.getBeanName(), beanName))
                .findAny().orElseThrow(NoSuchBeanException::new);
    }

    public String[] getBeanDefinitionNames() {
        return beanDefinitions.stream()
                .map(BeanDefinition::getBeanName)
                .toArray(String[]::new);
    }

    class BeanBuilder {
        private BeanDefinition beanDefinition;
        private Object bean;

        public BeanBuilder(BeanDefinition beanDefinition) {
            this.beanDefinition = beanDefinition;
        }

        private void createNewBeanInstance() {
            Class<?> type = beanDefinition.getBeanType();
            Constructor<?> constructor = type.getDeclaredConstructors()[0];
            Object newBean = null;
            if (constructor.getParameterCount() == 0) {
                newBean = createBeanWithDefaultConstructor(type);
            } else {
                newBean = createBeanWithConstructorWithParams(type);
            }
            bean = newBean;
        }

        private Object createBeanWithDefaultConstructor(Class<?> type) {
            Object newBean;
            try {
                newBean = type.newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
            return newBean;
        }

        private Object createBeanWithConstructorWithParams(Class<?> type) {
            Constructor<?> constructor = type.getDeclaredConstructors()[0];
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] paramsVal = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> paramType = parameterTypes[i];
                String beanName = getBeanNameByType(paramType);
                paramsVal[i] = getBean(beanName);
            }

            Object newBean;
            try {
                newBean = constructor.newInstance(paramsVal);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
            return newBean;
        }

        private String getBeanNameByType(Class<?> paramType) {
            System.out.println(paramType);
            String paramTypeName = paramType.getSimpleName();
            String localBeanName
                    = Character.toLowerCase(paramTypeName.charAt(0)) + paramTypeName.substring(1);
            return localBeanName;
        }

        private void callPostConstructAnnotatedMethod() {
            Stream.of(bean.getClass().getMethods())
                    .filter(e -> e.isAnnotationPresent(MyPostConstruct.class))
                    .forEach(e -> {
                        try {
                            e.invoke(bean);
                        } catch (IllegalAccessException | InvocationTargetException e1) {
                            throw new RuntimeException();
                        }
                    });
        }

        private void callInitMethod() {
            Class<?> beanType = bean.getClass();
            try {
                Method initMethod = beanType.getMethod("init");
                initMethod.invoke(bean);
            } catch (NoSuchMethodException e) {
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }


        private void createBenchmarkProxy() {
            Class<?> beanClass = bean.getClass();
            if (Arrays
                    .stream(beanClass.getMethods())
                    .anyMatch(e -> e.isAnnotationPresent(Benchmark.class) && e.getAnnotation(Benchmark.class).enable())) {
                bean = Proxy.newProxyInstance(
                        ClassLoader.getSystemClassLoader(),
                        beanClass.getInterfaces(),
                        (proxy, method, args) -> {

                            Method beanMethod = beanClass.getMethod(method.getName(), method.getParameterTypes());
                            beanMethod.isAnnotationPresent(Benchmark.class);
                            System.out.println(method.getName());
                            return method.invoke(bean, args);
                        }

                );
            }
        }

        public Object build() {
            return bean;
        }

        public void createTweetServiceProxy() {
            bean = new PrototypeTweetServiceProxy(
                    (TweetService) bean,
                    ApplicationContext.this).createProxy();

        }
    }
}
