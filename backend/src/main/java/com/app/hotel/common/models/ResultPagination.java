package com.app.hotel.common.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultPagination<T> {
    private T data;
    private Pagination pagination;

    // Constructor, Getters y Setters
    public ResultPagination(T data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    // Getters and Setters...
}
