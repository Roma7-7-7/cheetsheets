package cheetsheets.dk;

import cheetsheets.dk.core.GeneralExceptionMapper;
import cheetsheets.dk.core.User;
import cheetsheets.dk.db.UserDAO;
import cheetsheets.dk.health.SimpleJavaHealthCheck;
import cheetsheets.dk.resources.PingResource;
import cheetsheets.dk.resources.UserResource;
import cheetsheets.dk.security.BasicAuthenticator;
import cheetsheets.dk.security.SimpleAuthorizer;
import cheetsheets.dk.service.UserService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jdbi.v3.core.Jdbi;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CheetsheetApplication extends Application<CheetsheetConfiguration> {

    private final String name = "Cheetsheet Application";
    private static final String BASIC_AUTH_REALM = "BASIC-AUTH-REALM";

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
        jdbi.registerRowMapper(new UserDAO.UserRoleMapper());

        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final UserService userService = new UserService(jdbi.onDemand(UserDAO.class));

        environment.jersey().register(new PingResource());
        environment.jersey().register(new UserResource(userService));
        environment.jersey().register(new GeneralExceptionMapper());

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new BasicAuthenticator(userService, passwordEncoder))
                .setAuthorizer(new SimpleAuthorizer())
                .setRealm(BASIC_AUTH_REALM)
                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        environment.healthChecks().register("java", simpleJavaHealthCheck);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
