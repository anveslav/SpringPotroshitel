package quoters;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

  }
}
