package quoters;

/**
 * Created by Andrey on 02.12.2017.
 */
public class TerminatorQuoter implements Quoter {

  private String message;

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public void sayQuote() {
    System.out.println("message = " + message);
  }
}
