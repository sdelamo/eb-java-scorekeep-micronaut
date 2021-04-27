package scorekeep;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

public class MoveNotFoundException extends HttpStatusException {
  private String moveId;
  public MoveNotFoundException(String moveId) {
    super(HttpStatus.NOT_FOUND, "Move does not exist.");
    this.moveId = moveId;
  } 
  public String getMoveId()
  {
    return moveId;
  }
}