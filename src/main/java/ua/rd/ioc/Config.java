package ua.rd.ioc;

import java.util.List;

public interface Config {

    BeanDefinition[] EMPTY_BEANDEFINITION = new BeanDefinition[0];

    List<BeanDefinition> beanDefinitions();
}
