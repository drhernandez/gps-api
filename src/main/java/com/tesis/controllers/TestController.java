package com.tesis.controllers;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestController {

    public static Logger logger = LoggerFactory.getLogger(TestController.class);

    public void sendSms() {

        String tillUrl = System.getenv("TILL_URL");
        logger.info("SENDING TEST SMS WITH URL {}", tillUrl);
        HttpResponse<JsonNode> response = Unirest.post(tillUrl).body("{\n" +
                "    \"phone\": [\"+5493515495416\", \"5493515495416\", \"3515495416\"],\n" +
                "    \"text\" : \"Hello Heroku!\"\n" +
                "}")
                .asJson();

        logger.info("RESPONSE >>>>> STATUS: {}", response.getStatus());
    }
}
