package com.okular.jserv;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class Configurator {

    private final Properties properties = new Properties();

    @PostConstruct
    private void initProperties() {
        //Map<String, String> env = System.getenv();
        //String applicationConfigDir = env.get("EXTERNALIZED_APPLICATION_CONFIG_DIR");
        try (final InputStream appConfig = new FileInputStream("/config/jserv/application.properties");
             final InputStream credentialsConfig = new FileInputStream("/config/jserv/credentials.properties")) {
            properties.load(appConfig);
            properties.load(credentialsConfig);
        } catch (IOException e) {
            throw new RuntimeException("Could not init configuration", e);
        }
    }

    @Produces
    @Config("unused")
    public String exposeConfig(InjectionPoint injectionPoint) {
        final Config config = injectionPoint.getAnnotated().getAnnotation(Config.class);
        if (config != null)
            return properties.getProperty(config.value());
        return null;
    }

}
