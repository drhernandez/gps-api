package com.tesis.exceptions;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataException extends Exception {

    private static final long serialVersionUID = 1L;
    private static final Gson gson = new Gson();

    private String error;
    private String message;

    public DataException() {
    }

    public DataException(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public DataException(String error, String message, Throwable cause) {
        super(cause);
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String buildCauseMessage() {
        Throwable t = this.getCause() != null ? this.getCause() : this;
        return t.toString() + " at " + t.getStackTrace()[0].toString();
    }

    public String toJson() {
        Map<String, Object> exceptionMap = new LinkedHashMap<>();

        exceptionMap.put("error", error);
        exceptionMap.put("message", message);
        exceptionMap.put("cause", buildCauseMessage());

        try {
            return gson.toJson(exceptionMap);
        } catch (Exception exception) {
            return "{" + "\"error\"" + error + "\"message\"" +
                    message + "}";
        }
    }
}
