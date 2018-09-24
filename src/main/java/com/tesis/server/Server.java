package com.tesis.server;

import com.tesis.routes.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class Server {

    private final Logger log = LoggerFactory.getLogger(Server.class);
    private int port;
    private Router router;
    private int maxProcessorsMult;
    private int minProcessorsMult;
    private int idleTimeoutMillis;

    /**
     * Run the application: init appConfig y endpoints.
     */
    public void run() {

        final int processors = Runtime.getRuntime().availableProcessors();
        final int maxThreads = processors * getMaxProcessorsMult();
        final int minThreads = processors * getMinProcessorsMult();
        final int timeout = getIdleTimeoutMillis();

        Spark.port(getPort());
        Spark.threadPool(maxThreads, minThreads, timeout);
        log.info("Using thread pool: [min:{} | max:{} | timeout:{}]", minThreads, maxThreads, timeout);

        log.info("Setting up routes...");
        setUpRoutes();

//        log.info("App Run: localhost:" + port);
        System.out.println("Running...");
    }

    private void setUpRoutes() {
        router.init();
    }

    public int getMaxProcessorsMult() {
        return maxProcessorsMult;
    }

    public void setMaxProcessorsMult(int maxProcessorsMult) {
        this.maxProcessorsMult = maxProcessorsMult;
    }

    public int getMinProcessorsMult() {
        return minProcessorsMult;
    }

    public void setMinProcessorsMult(int minProcessorsMult) {
        this.minProcessorsMult = minProcessorsMult;
    }

    public int getIdleTimeoutMillis() {
        return idleTimeoutMillis;
    }

    public void setIdleTimeoutMillis(int idleTimeoutMillis) {
        this.idleTimeoutMillis = idleTimeoutMillis;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
