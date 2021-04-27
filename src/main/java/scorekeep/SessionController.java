package scorekeep;

import io.micronaut.http.annotation.*;

import java.util.List;

@Controller(value="/api/session")
public class SessionController {
  private final SessionFactory sessionFactory = new SessionFactory();
  private final SessionModel model = new SessionModel();

  /* POST /session */
  @Post
  public Session newSession() {
    Session session = sessionFactory.newSession();
    return session;
  }
  /* PUT /session/SESSION */
  @Put(value="/{sessionId}")
  public Session updateSession(@PathVariable String sessionId, @Body Session session) {
    model.saveSession(session);
    return session;
  }
  /* GET /session */
  @Get
  public List<Session> getSessions() {
    return sessionFactory.getSessions();
  }
  /* GET /session/SESSION/ */
  @Get(value="/{sessionId}")
  public Session getSession(@PathVariable String sessionId) throws SessionNotFoundException {
    return sessionFactory.getSession(sessionId);
  }
  /* DELETE /session/SESSION/ */
  @Delete(value="/{sessionId}")
  public void deleteSession(@PathVariable String sessionId) throws SessionNotFoundException {
    model.deleteSession(sessionId);
  }
  /* PUT /session/SESSION/owner/USER */
  @Put(value="/{sessionId}/owner/{ownerId}")
  public Session setOwner(@PathVariable String sessionId, @PathVariable String ownerId) throws SessionNotFoundException {
    Session session = sessionFactory.getSession(sessionId);
    session.setOwner(ownerId);
    model.saveSession(session);
    return session;
  }
  /* PUT /session/SESSION/game/GAME */
  @Put(value="/{sessionId}/game/{gameId}")
  public void setSessionGame(@PathVariable String sessionId, @PathVariable String gameId) throws SessionNotFoundException, GameNotFoundException {
    Session session = sessionFactory.getSession(sessionId);
    session.addGame(gameId);
    model.saveSession(session);
  }
}
