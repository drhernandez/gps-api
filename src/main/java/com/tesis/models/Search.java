package com.tesis.models;

import java.util.ArrayList;
import java.util.List;

public class Search<T> {

    private List<T> data = new ArrayList<>();
    private Paging paging;

    public Search() {
    }

    public Search(List<T> data, Paging paging) {
        this.data = data;
        this.paging = paging;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
