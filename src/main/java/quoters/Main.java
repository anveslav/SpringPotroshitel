package quoters;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andrey on 02.12.2017.
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "context.xml");
      context.getBean(Quoter.class).sayQuote();

  }

}
