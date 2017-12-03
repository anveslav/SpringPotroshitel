package quoters;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class ProperyFileApplicationContext extends GenericApplicationContext {

  public ProperyFileApplicationContext(String fileName) {
    PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(
        this);
    int i = reader.loadBeanDefinitions(fileName);
    System.out.println("found " + i + " beans");
    refresh();
  }




  public static void main(String[] args) {
    ProperyFileApplicationContext context = new ProperyFileApplicationContext(
        "context.properties");
    context.getBean(Quoter.class).sayQuote();
  }
}
