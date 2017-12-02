package quoters;

/**
 * Created by Andrey on 02.12.2017.
 */
public class ProfilingController implements ProfilingControllerMBean {

  private boolean enabled = true;

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

}
