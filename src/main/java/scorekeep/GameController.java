package scorekeep;

import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller(value="/api/game/{sessionId}")
/** Routes for game CRUD
 Use GameFactory methods to create new games and load existing games
 Use Game class methods to set fields on Game objects
 Use GameModel to persist updated Game objects to DynamoDB
 **/
public class GameController {
  private final GameFactory gameFactory = new GameFactory();
  private final RulesFactory rulesFactory = new RulesFactory();
  private final StateFactory stateFactory = new StateFactory();
  private final GameModel model = new GameModel();
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  /* GET /game/SESSION/ */
  @Get
  public List<Game> getGames(@PathVariable String sessionId) throws SessionNotFoundException {
    return gameFactory.getGames(sessionId);
  }
  /* POST /game/SESSION/ */
  @Post
  public Game newGame(@PathVariable String sessionId) throws SessionNotFoundException, GameNotFoundException {
    logger.info("Creating game");
    return gameFactory.newGame(sessionId);
  }
  /*  GET /game/SESSION/GAME */
  @Get("/{gameId}")
  public Game getGame(@PathVariable String sessionId, @PathVariable String gameId) throws SessionNotFoundException, GameNotFoundException {
    return gameFactory.getGame(sessionId, gameId);
  }
  /*  PUT /game/SESSION/GAME */
  @Put("/{gameId}")
  public Game updateGame(@PathVariable String sessionId, @PathVariable String gameId, @Body Game game) throws SessionNotFoundException, GameNotFoundException {
    model.saveGame(game);
    return game;
  }
  /*  DELETE /game/SESSION/GAME */
  @Delete("/{gameId}")
  public void deleteGame(@PathVariable String sessionId, @PathVariable String gameId) throws GameNotFoundException {
    model.deleteGame(sessionId, gameId);
  }
  /*  GET /game/SESSION/GAME/name */
  @Get("/{gameId}/name")
  public String getGameName(@PathVariable String sessionId, @PathVariable String gameId) throws SessionNotFoundException, GameNotFoundException {
    return gameFactory.getGame(sessionId, gameId).getName();
  }
  /*  PUT /game/SESSION/GAME/name/NAME */
  @Put("/{gameId}/name/{name}")
  public void setGameName(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String name) throws SessionNotFoundException, GameNotFoundException {
    Game game = gameFactory.getGame(sessionId, gameId);
    game.setName(name);
    model.saveGame(game);
  }
  /*  GET /game/SESSION/GAME/rules */
  @Get("/{gameId}/rules")
  public String getGameRules(@PathVariable String sessionId, @PathVariable String gameId) throws SessionNotFoundException, GameNotFoundException {
    return gameFactory.getGame(sessionId, gameId).getRules();
  }
  /*  PUT /game/SESSION/GAME/rules/RULES */
  @Put("/{gameId}/rules/{rulesId}")
  public void setGameRules(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String rulesId) throws SessionNotFoundException, GameNotFoundException {
    Game game = gameFactory.getGame(sessionId, gameId);
    logger.info("setting rules");
    game.setRules(rulesId);
    String initialState = rulesFactory.getRules(rulesId).getInitialState();
    logger.info("initialState: " + initialState);
    // create new state with initial state
    State state = stateFactory.newState(sessionId, gameId, initialState, game.getUsers());
    game.setState(state.getId());
    model.saveGame(game);
  }
  /*  GET /game/SESSION/GAME/user */
  @Get("/{gameId}/user")
  public Set<String> getGameUsers(@PathVariable String sessionId, @PathVariable String gameId) throws SessionNotFoundException, GameNotFoundException {
    return gameFactory.getGame(sessionId, gameId).getUsers();
  }
  /*  PUT /game/SESSION/GAME/user/USER */
  @Put("/{gameId}/user/{user}")
  public void setGameUser(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String user) throws SessionNotFoundException, GameNotFoundException {
    Game game = gameFactory.getGame(sessionId, gameId);
    game.setUser(user);
    model.saveGame(game);
  }
  /*  POST /game/SESSION/GAME/users */
  @Post("/{gameId}/users")
  public void setGameUsers(@PathVariable String sessionId, @PathVariable String gameId, @Body Set<String> users) throws SessionNotFoundException, GameNotFoundException {
    Game game = gameFactory.getGame(sessionId, gameId);
    game.setUsers(users);
    model.saveGame(game);
  }
  /** PUT /game/SESSION/GAME/starttime/STARTTIME **/
  @Put("/{gameId}/starttime/{startTime}")
  public void setStartTime(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String startTime) throws SessionNotFoundException, GameNotFoundException, NumberFormatException {
    Game game = gameFactory.getGame(sessionId, gameId);
    Long seconds = Long.parseLong(startTime);
    Date date = new Date(seconds);
    logger.info("Setting start time.");
    game.setStartTime(date);
    logger.info("Start time: " + game.getStartTime());
    model.saveGame(game);
  }
  /** PUT /game/SESSION/GAME/endtime/ENDTIME **/
  @Put("/{gameId}/endtime/{endTime}")
  public void setEndTime(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String endTime) throws SessionNotFoundException, GameNotFoundException, NumberFormatException {
    Game game = gameFactory.getGame(sessionId, gameId);
    Long seconds = Long.parseLong(endTime);
    Date date = new Date(seconds);
    game.setEndTime(date);
    model.saveGame(game);
  }
  /** PUT /game/SESSION/GAME/move/MOVE **/
  @Put("/{gameId}/move/{moveId}")
  public void setGameMove(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String moveId) throws SessionNotFoundException, GameNotFoundException {
    Game game = gameFactory.getGame(sessionId, gameId);
    game.setMove(moveId);
    model.saveGame(game);
  }
  /** PUT /game/SESSION/GAME/state/STATE **/
  @Put("/{gameId}/state/{stateId}")
  public void setGameState(@PathVariable String sessionId, @PathVariable String gameId, @PathVariable String stateId) throws SessionNotFoundException, GameNotFoundException {
    Game game = gameFactory.getGame(sessionId, gameId);
    game.setState(stateId);
    model.saveGame(game);
  }
}
