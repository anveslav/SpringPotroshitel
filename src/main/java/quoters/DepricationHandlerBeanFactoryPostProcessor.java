package quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by Andrey on 03.12.2017.
 */
public class DepricationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  @Override
  public void postProcessBeanFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    String[] names = configurableListableBeanFactory.getBeanDefinitionNames();
    for (String name : names) {
      BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(name);
      String beanClassName = beanDefinition.getBeanClassName();
      try {
        Class<?> beanClass = Class.forName(beanClassName);
        DepricatedClass annotation = beanClass.getAnnotation(DepricatedClass.class);
        if (annotation!=null){
          beanDefinition.setBeanClassName(annotation.newImpl().getName());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }


    }
  }
}
