package com.tesis.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import kong.unirest.Unirest;
import kong.unirest.UnirestInstance;

public class UnirestConfig extends AbstractModule {

    @Provides @Singleton
    private UnirestInstance getUnirest() {

        UnirestInstance defaultInstance = Unirest.spawnInstance();
        defaultInstance.config()
                .socketTimeout(500)
                .connectTimeout(1000)
                .concurrency(100, 20)
                .automaticRetries(true);

        return defaultInstance;
    }
}
