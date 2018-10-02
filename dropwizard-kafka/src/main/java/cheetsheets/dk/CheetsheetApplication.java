package cheetsheets.dk;

import cheetsheets.dk.core.GeneralExceptionMapper;
import cheetsheets.dk.db.UserDAO;
import cheetsheets.dk.health.SimpleJavaHealthCheck;
import cheetsheets.dk.resources.PingResource;
import cheetsheets.dk.resources.UserResource;
import cheetsheets.dk.service.UserService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class CheetsheetApplication extends Application<CheetsheetConfiguration> {

    private final String name = "Cheetsheet Application";

    public static void main(String[] args) throws Exception {
        new CheetsheetApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CheetsheetConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<CheetsheetConfiguration>() {
            public DataSourceFactory getDataSourceFactory(CheetsheetConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        final SubstitutingSourceProvider substitutingSourceProvider =
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false));
        bootstrap.setConfigurationSourceProvider(substitutingSourceProvider);

        bootstrap.addBundle(new AssetsBundle("/assets/resources/css", "/resources/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/templates", "/", "index.html", "templates"));
    }

    public void run(CheetsheetConfiguration config, Environment environment) throws Exception {
        final SimpleJavaHealthCheck simpleJavaHealthCheck = new SimpleJavaHealthCheck();

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
        jdbi.registerRowMapper(new UserDAO.UserMapper());

        final UserService userService = new UserService(jdbi.onDemand(UserDAO.class));

        environment.jersey().register(new PingResource());
        environment.jersey().register(new UserResource(userService));
        environment.jersey().register(new GeneralExceptionMapper());

        environment.healthChecks().register("java", simpleJavaHealthCheck);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
