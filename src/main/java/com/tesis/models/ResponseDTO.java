package com.tesis.models;

import com.tesis.utils.JsonUtils;
import com.tesis.exceptions.ApiException;

public class ResponseDTO<T> {

    public T model;
    public ApiException error;

    public ResponseDTO() {
    }

    public ResponseDTO(T model, ApiException error) {
        this.model = model;
        this.error = error;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public ApiException getError() {
        return error;
    }

    public void setError(ApiException error) {
        this.error = error;
    }

    public String getModelAsJson() {
        return JsonUtils.INSTANCE.GSON().toJson(this.model);
    }
}
