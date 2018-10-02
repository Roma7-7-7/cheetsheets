package cheetsheets.dk.health;

import com.codahale.metrics.health.HealthCheck;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJavaHealthCheck extends HealthCheck {

    private static final Logger logger = LoggerFactory.getLogger(SimpleJavaHealthCheck.class);

    private static final String ERROR_MESSAGE = "Health check failed";

    protected Result check() throws Exception {
        try {
            LocalDateTime.now();
            return Result.healthy();
        } catch (Exception e) {
            logger.error(ERROR_MESSAGE, e);
            return Result.unhealthy(ERROR_MESSAGE);
        }
    }
}
