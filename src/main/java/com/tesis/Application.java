package com.tesis;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tesis.config.ConfigModule;
import com.tesis.server.Server;
import spark.Spark;
import spark.servlet.SparkApplication;

public class Application implements SparkApplication {

    @Override
    public void init() {

        Injector injector = Guice.createInjector(new ConfigModule());
        Server server  = injector.getInstance(Server.class);
        server.run();

    }

    @Override
    public void destroy() {
        Spark.stop();
    }
}
