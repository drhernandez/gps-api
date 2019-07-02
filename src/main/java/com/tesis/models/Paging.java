package com.tesis.models;

public class Paging {
    private int page;
    private int limit;
    private int total;

    public Paging(int page, int limit, int total) {
        this.page = page;
        this.limit = limit;
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
