package scorekeep;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

public class RulesException extends HttpStatusException  {
  private String rulesId;
  public RulesException(String rulesId) {
    super(HttpStatus.BAD_REQUEST, "Rules move invocation failed.");
    this.rulesId = rulesId;
  }
  public String getRulesId()
  {
    return rulesId;
  }
}