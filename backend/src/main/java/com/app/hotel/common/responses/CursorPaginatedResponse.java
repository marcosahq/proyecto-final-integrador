package com.app.hotel.common.responses;

import lombok.Getter;

public class CursorPaginatedResponse<T> extends ApiResponse<T> {
    @Getter
    private final String nextCursor;
    @Getter
    private final String previousCursor;
    private final boolean hasNextPage;
    private final boolean hasPreviousPage;

    public CursorPaginatedResponse(boolean success, String message, T result, String nextCursor, String previousCursor, boolean hasNextPage, boolean hasPreviousPage) {
        super(success, message, result);
        this.nextCursor = nextCursor;
        this.previousCursor = previousCursor;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean hasNextPage() {
        return hasNextPage;
    }

    public boolean hasPreviousPage() {
        return hasPreviousPage;
    }
}

