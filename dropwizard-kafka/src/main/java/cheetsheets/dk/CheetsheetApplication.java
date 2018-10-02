package cheetsheets.dk;

import cheetsheets.dk.health.SimpleJavaHealthCheck;
import cheetsheets.dk.resources.PingResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CheetsheetApplication extends Application<CheetsheetConfiguration> {

    private final String name = "Cheetsheet Application";

    public static void main(String[] args) throws Exception {
        new CheetsheetApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CheetsheetConfiguration> bootstrap) {
    }

    public void run(CheetsheetConfiguration applicationConfiguration, Environment environment) throws Exception {
        final PingResource pingResource = new PingResource();
        final SimpleJavaHealthCheck simpleJavaHealthCheck = new SimpleJavaHealthCheck();
        environment.jersey().register(pingResource);
        environment.healthChecks().register("Simple Java Health Check", simpleJavaHealthCheck);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
