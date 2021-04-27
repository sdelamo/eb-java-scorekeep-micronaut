package scorekeep;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

public class StateNotFoundException extends HttpStatusException {
  private String stateId;
  public StateNotFoundException(String stateId) {
    super(HttpStatus.NOT_FOUND, "State does not exist.");
    this.stateId = stateId;
  } 
  public String getStateId()
  {
    return stateId;
  }
}