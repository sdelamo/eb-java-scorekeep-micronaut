package scorekeep;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller("/api/user")
public class UserController {
  private final UserFactory factory = new UserFactory();
  private final UserModel model = new UserModel();

  /* POST /user */
  @Post
  public User newUser(@Nullable @Body User userbody) throws IOException {
    User user;
    if ( userbody == null || userbody.getName() == null ){
      user = factory.newUser();
    } else {
      user = factory.newUser(userbody.getName());
    }
    return user;
  }
  /* PUT /user/USER */
  @Put(value="/{userId}")
  public User updateUser(@PathVariable String userId, @Body User user) {
    model.saveUser(user);
    return user;
  }
  /* GET /user */
  @Get
  public List<User> getUsers() {
    return factory.getUsers();
  }
  /* GET /user/USER */
  @Get("/{userId}")
  public User getUser(@PathVariable String userId) throws UserNotFoundException {
    return factory.getUser(userId);
  }
  /* DELETE /user/USER */
  @Delete("/{userId}")
  public void deleteUser(@PathVariable String userId) throws UserNotFoundException {
    model.deleteUser(userId);
  }
}
