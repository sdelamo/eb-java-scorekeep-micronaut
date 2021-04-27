package scorekeep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserFactory {
  private final UserModel model = new UserModel();

  public UserFactory(){
  }

  public User newUser() throws IOException {
    String id = Identifiers.random();
    User user = new User(id);
    String name = randomName();
    user.setName(name);
    model.saveUser(user);
    return user;
  }

  public User newUser(String name) throws IOException {
    String id = Identifiers.random();
    User user = new User(id);
    user.setName(name);
    model.saveUser(user);
    return user;
  }

  public String randomName() {
    List<String> names = new ArrayList<String>();
    
    names.add("Billy");
    names.add("Jake");
    names.add("Emma");
    names.add("Ralph");
    names.add("Lucy");
    
    Random random = new Random();
    int index = random.nextInt(names.size());
    String name = names.get(index);

    Sns.sendNotification("Scorekeep user created", "Name: " + name);
    return name;
  }

  public User getUser(String userId) throws UserNotFoundException {
    return model.loadUser(userId);
  }

  public List<User> getUsers() {
    return model.loadUsers();
  }
}