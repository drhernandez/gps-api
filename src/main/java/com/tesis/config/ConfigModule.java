package com.tesis.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.sun.deploy.config.Config;
import com.tesis.routes.Router;
import com.tesis.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigModule extends AbstractModule {

    private Logger logger = LoggerFactory.getLogger(ConfigModule.class);

    public static final String MODULE_NAME = "GEOAPI";

    @Override
    protected void configure() {
        super.configure();
    }

    @Provides
    Server provideMappingRouter(Router router) {

        ProcessBuilder processBuilder = new ProcessBuilder();
        Server server = new Server();
        server.setMaxProcessorsMult(100);
        server.setMinProcessorsMult(20);
        server.setIdleTimeoutMillis(2000);
        server.setPort(processBuilder.environment().get("PORT") != null ? Integer.parseInt(processBuilder.environment().get("PORT")) : 8080);
        server.setRouter(router);

        return server;
    }
}
