package com.tesis.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.tesis.clients.StorageClient;
import com.tesis.clients.imp.StorageClientImp;
import com.tesis.routes.DevicesRouter;
import com.tesis.routes.GpsRouter;
import com.tesis.routes.Router;
import com.tesis.server.Server;
import com.tesis.services.DevicesService;
import com.tesis.services.StorageService;
import com.tesis.services.imp.DevicesServiceImp;
import com.tesis.services.imp.StorageServiceImp;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigModule extends AbstractModule {

    private Logger logger = LoggerFactory.getLogger(ConfigModule.class);

    public static final String MODULE_NAME = "GEOAPI";

    @Override
    protected void configure() {

        //bind routers
        bind(RouteGroup.class).annotatedWith(Names.named("devices-router")).to(DevicesRouter.class);
        bind(RouteGroup.class).annotatedWith(Names.named("gps-router")).to(GpsRouter.class);

        //bind services
        bind(StorageService.class).to(StorageServiceImp.class);
        bind(DevicesService.class).to(DevicesServiceImp.class);

        //bind clients
        bind(StorageClient.class).to(StorageClientImp.class);
    }

    @Provides
    Server provideServer(Router router) {

        ProcessBuilder processBuilder = new ProcessBuilder();
        Server server = new Server();
        server.setMaxProcessorsMult(100);
        server.setMinProcessorsMult(20);
        server.setIdleTimeoutMillis(2000);
        server.setPort(processBuilder.environment().get("PORT") != null ? Integer.parseInt(processBuilder.environment().get("PORT")) : 8080);
        server.setRouter(router);

        return server;
    }

    @Provides @Singleton
    Configuration provideDBConfiguration() throws SQLException {

        String userName = "ferjobfmeqjprm";
        String password = "ec949c7ef858b46668966285a5cfa165e42d8a481444c6f8a42b0f1c0b481076";
        String url = "jdbc:postgresql://ec2-23-21-128-35.compute-1.amazonaws.com:5432/d6em601ppakco6?sslmode=require";
        Connection connection =  DriverManager.getConnection(url, userName, password);

        return new DefaultConfiguration().set(connection).set(SQLDialect.POSTGRES_10);
    }
}
