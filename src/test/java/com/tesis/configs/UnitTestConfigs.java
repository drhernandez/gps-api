package com.tesis.configs;

import com.google.inject.Guice;
import com.tesis.config.ConfigModule;

public class UnitTestConfigs {

    public UnitTestConfigs() {
        Guice.createInjector(new ConfigModule());
    }
}
