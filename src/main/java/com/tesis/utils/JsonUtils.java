package com.tesis.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import io.jsonwebtoken.Claims;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

public enum JsonUtils {

    INSTANCE;

    private Gson gson;

    JsonUtils() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .disableHtmlEscaping()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    public Gson GSON() {
        return gson;
    }

    public <T> List<T> parseJsonList(JsonElement json, Class<T> clazz) {
        return gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
    }

    public <T> T parseJson(JsonElement json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public <T> T parseJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
