package postProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        //BeanFactoryPostProcessor可以修改BEAN的配置信息而BeanPostProcessor不能
        //我们在这里修改postProcessorBean的username注入属性
        System.out.println("beanFactoryPostProcessor..");
        BeanDefinition bd = beanFactory.getBeanDefinition("postProcessorBean");
        MutablePropertyValues pv = bd.getPropertyValues();
        if (pv.contains("username")) {
            pv.addPropertyValue("username", "xiaojun");
        }

    }

}
