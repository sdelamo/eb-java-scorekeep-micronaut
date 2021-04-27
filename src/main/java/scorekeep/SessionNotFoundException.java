package scorekeep;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

public class SessionNotFoundException extends HttpStatusException  {
  private String sessionId;
  public SessionNotFoundException(String sessionId)
  {
    super(HttpStatus.NOT_FOUND, "Session does not exist.");
    this.sessionId = sessionId;
  } 
  public String getSessionId()
  {
    return sessionId;
  }
}