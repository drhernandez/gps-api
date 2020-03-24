package com.tesis.exceptions;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final Gson gson = new Gson();

    private String error;
    private String message;
    private Integer status = HttpStatus.INTERNAL_SERVER_ERROR_500;

    public ApiException() {
    }

    public ApiException(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public ApiException(String error, String message, Integer status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public ApiException(String error, String message, Integer status, Throwable cause) {
        super(cause);
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public ApiException(String error, String message, Throwable cause) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String buildCauseMessage() {
        Throwable t = this.getCause() != null ? this.getCause() : this;
        return t.toString() + " at " + t.getStackTrace()[0].toString();
    }

    public String toJson() {
        Map<String, Object> exceptionMap = new LinkedHashMap<>();

        exceptionMap.put("error", error);
        exceptionMap.put("message", message);
        exceptionMap.put("status", status);
        exceptionMap.put("cause", buildCauseMessage());

        try {
            return gson.toJson(exceptionMap);
        } catch (Exception exception) {
            return "{" + "\"error\"" + error + "\"message\"" +
                    message + "\"status\"" + status + "}";
        }
    }
}
