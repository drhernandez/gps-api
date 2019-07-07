package com.tesis.models;

import java.util.ArrayList;
import java.util.List;

public class Search<T> {

    private List<T> data = new ArrayList<>();
    private Pagination paging;

    public Search() {
        paging = new Pagination();
    }

    public Search(List<T> data, Pagination paging) {
        this.data = data;
        this.paging = paging;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Pagination getPaging() {
        return paging;
    }

    public void setPaging(Pagination paging) {
        this.paging = paging;
    }
}
