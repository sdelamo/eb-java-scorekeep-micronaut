package scorekeep;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

public class GameNotFoundException extends HttpStatusException {
  private String gameId;
  public GameNotFoundException(String gameId) {
    super(HttpStatus.NOT_FOUND, "Game does not exist.");
    this.gameId = gameId;
  } 
  public String getGameId()
  {
    return gameId;
  }
}