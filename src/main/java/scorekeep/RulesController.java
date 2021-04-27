package scorekeep;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import java.util.*;

@Controller(value="/api/rules")
public class RulesController {
  private final RulesFactory rulesFactory = new RulesFactory();

  /* GET /rules */
  @Get
  public Collection<Rules> rules() {
    return rulesFactory.getAllRules();
  }
  /* GET /rules/RULES */
  @Get(value="/{rulesId}")
  public Rules rules(@PathVariable String rulesId) {
    return rulesFactory.getRules(rulesId);
  }
}
