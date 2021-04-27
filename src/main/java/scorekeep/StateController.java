package scorekeep;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import java.util.List;

@Controller(value="/api/state/{sessionId}/{gameId}")
public class StateController {
  private final StateFactory stateFactory = new StateFactory();
  private final StateModel model = new StateModel();
  private final GameController gameController = new GameController();

  /* GET /state/SESSION/GAME */
  @Get
  public List<State> getStates(@PathVariable String sessionId, @PathVariable String gameId) throws SessionNotFoundException, GameNotFoundException {
    return stateFactory.getStates(sessionId, gameId);
  }
  /* GET /state/SESSION/GAME/STATE */
  @Get(value="/{stateId}")
  public State getState(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String stateId) throws SessionNotFoundException, GameNotFoundException, StateNotFoundException {
    return stateFactory.getState(sessionId, gameId, stateId);
  }
}
