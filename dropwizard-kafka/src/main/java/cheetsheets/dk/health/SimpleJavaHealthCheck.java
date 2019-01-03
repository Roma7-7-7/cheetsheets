package cheetsheets.dk.health;

import com.codahale.metrics.health.HealthCheck;
import org.joda.time.LocalDateTime;

public class SimpleJavaHealthCheck extends HealthCheck {

    protected Result check() throws Exception {
        LocalDateTime.now();
        return Result.healthy();
    }
}
