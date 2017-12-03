package screensaver;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Andrey on 03.12.2017.
 */
@Component
public class CustomScopeRegestryBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

  @Override
  public void postProcessBeanFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    configurableListableBeanFactory.registerScope("periodical", new PeriodicalScopeConfigurer());
  }
}
