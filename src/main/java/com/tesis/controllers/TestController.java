package com.tesis.controllers;

import com.google.common.collect.Lists;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestParsingException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TestController {

    public static Logger logger = LoggerFactory.getLogger(TestController.class);

    public void sendSms() {

        String tillUrl = System.getenv("TILL_URL");
        String body = "{\n" +
                "    \"phone\": [\"5493515495416\", \"3515495416\"],\n" +
//                "    \"method\" : \"SMS\"\n" +
                "    \"text\" : \"Hello Heroku!\"\n" +
                "}";
        logger.info("SENDING TEST SMS WITH URL {} BODY {}", tillUrl, body);
        HttpResponse<JsonNode> response = Unirest.post(tillUrl).body(body)
                .asJson();

        logger.info("RESPONSE >>>>> STATUS: {}", response.getStatus());

        if (response.getParsingError().isPresent()) {
            UnirestParsingException error = response.getParsingError().get();
            throw error;
        }
    }
}
