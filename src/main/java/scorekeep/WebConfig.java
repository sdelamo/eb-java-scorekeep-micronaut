package scorekeep;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class WebConfig implements ApplicationEventListener<ServerStartupEvent> {
  private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

  @Override
  public void onApplicationEvent(ServerStartupEvent event) {
    if ( System.getenv("NOTIFICATION_EMAIL") != null ){
      try { Sns.createSubscription(); }
      catch (Exception e ) {
        logger.warn("Failed to create subscription for email "+  System.getenv("NOTIFICATION_EMAIL"));
      }
    }
  }
}
