package com.app.hotel.common.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OffsetPaginatedResponse<T> extends ResponseFactory<T> {
    private final int currentPage;
    private final int pageSize;
    private final long totalItems;

    public OffsetPaginatedResponse(boolean success, String message, T result, int currentPage, int pageSize, long totalItems) {
        super(success, message, result);
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
    }
}

