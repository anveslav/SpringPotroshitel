package quoters;

import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

  private Map<String, Class> map = new HashMap<String, Class>();
  private ProfilingController controller = new ProfilingController();

  public ProfilingHandlerBeanPostProcessor() throws Exception {
    MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
    mBeanServer.registerMBean(controller,new ObjectName("profiling","name", "controller"));

  }

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
              if (controller.isEnabled()) {
                System.out.println("Профилирую...");
                long before = System.nanoTime();
                Object retVal = method.invoke(o, args);
                long after = System.nanoTime();
                System.out.println(after - before);
                System.out.println("Всё!");
                return retVal;
              }else {
                return method.invoke(o,args);
              }
            }
          });
    }

    return null;
  }
}
