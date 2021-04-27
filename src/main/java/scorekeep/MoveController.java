package scorekeep;

import io.micronaut.http.annotation.*;

import java.util.List;

@Controller(value="/api/move/{sessionId}/{gameId}")
public class MoveController {
  private final MoveFactory moveFactory = new MoveFactory();
  private final MoveModel model = new MoveModel();
  private final GameController gameController = new GameController();
  /* GET /move/SESSION/GAME */
  @Get
  public List<Move> getMoves(@PathVariable String sessionId, @PathVariable String gameId) throws SessionNotFoundException, GameNotFoundException {
    return moveFactory.getMoves(sessionId, gameId);
  }
  /* POST /move/SESSION/GAME/USER ; move string */
  @Post("/{userId}")
  public Move newMove(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String userId, @Body String move) throws SessionNotFoundException, GameNotFoundException, StateNotFoundException, RulesException {
    return moveFactory.newMove(sessionId, gameId, userId, move);
  }
  /** GET /move/SESSION/GAME/MOVE **/
  @Get("/{moveId}")
  public Move getMove(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String moveId) throws SessionNotFoundException, GameNotFoundException, MoveNotFoundException {
    return moveFactory.getMove(sessionId, gameId, moveId);
  }
}
