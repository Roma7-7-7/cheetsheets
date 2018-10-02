package cheetsheets.dk;

import cheetsheets.dk.core.GeneralExceptionMapper;
import cheetsheets.dk.health.SimpleJavaHealthCheck;
import cheetsheets.dk.resources.PingResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CheetsheetApplication extends Application<CheetsheetConfiguration> {

    private final String name = "Cheetsheet Application";

    public static void main(String[] args) throws Exception {
        new CheetsheetApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CheetsheetConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/resources/css", "/resources/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/templates", "/", "index.html", "templates"));
    }

    public void run(CheetsheetConfiguration applicationConfiguration, Environment environment) throws Exception {
        final PingResource pingResource = new PingResource();
        final SimpleJavaHealthCheck simpleJavaHealthCheck = new SimpleJavaHealthCheck();
        environment.jersey().register(pingResource);
        environment.jersey().register(new GeneralExceptionMapper());
        environment.healthChecks().register("java", simpleJavaHealthCheck);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
