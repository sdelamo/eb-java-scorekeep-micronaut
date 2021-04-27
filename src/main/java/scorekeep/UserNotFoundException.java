package scorekeep;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

public class UserNotFoundException extends HttpStatusException {
  private String userId;

  public UserNotFoundException(String userId) {
    super(HttpStatus.NOT_FOUND, "User does not exist.");
    this.userId = userId;
  } 
  public String getUserId()
  {
    return userId;
  }
}