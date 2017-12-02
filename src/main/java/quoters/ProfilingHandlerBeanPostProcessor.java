package quoters;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by Andrey on 02.12.2017.
 */
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

  private Map<String, Class> map = new HashMap<String, Class>();
  private ProfilingController controller = new ProfilingController();

  @Override
  public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
    Class<?> beanClass = o.getClass();
    if (beanClass.isAnnotationPresent(Profiling.class)) {
      map.put(s, beanClass);
    }
    return o;
  }

  @Override
  public Object postProcessAfterInitialization(final Object o, String s) throws BeansException {
    final Class beanClass = map.get(s);
    if (beanClass != null) {
      return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
          new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              System.out.println("Профилирую....");
              long before = System.nanoTime();
              Object retVal = method.invoke(o, args);
              long after = System.nanoTime();
              System.out.println("Всё!");
              return retVal;
            }
          });
    }

    return null;
  }
}
