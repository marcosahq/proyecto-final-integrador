package com.app.hotel.common.responses;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseFactory<T> {
    // Getters y setters
    private boolean success;
    private String message;
    private T result;
    private LocalDateTime timestamp;
    private HttpStatus status;

    // Constructores
    public ResponseFactory(boolean success, String message, T result) {
        this.setSuccess(success);
        this.setMessage(message);
        this.setResult(result);
        this.setTimestamp(LocalDateTime.now());
    }

    // Respuesta exitosa sin paginación
    public static <T> ResponseFactory<T> success(T result) {
        return new ResponseFactory<>(true, "Operación correcta", result);
    }

    // Respuesta exitosa con mensaje personalizado
    public static <T> ResponseFactory<T> success(String message, T result) {
        return new ResponseFactory<>(true, message, result);
    }

    // Respuesta exitosa con paginación por Offset y Limit
    public static <T> OffsetPaginatedResponse<ResultPagination<T>> paginatedSuccessWithOffset(List<T> data, long total, int perPage, int currentPage, String baseUrl) {
        Pagination pagination = new Pagination(total, perPage, currentPage);
        ResultPagination<T> result = new ResultPagination<>(data, pagination, baseUrl);
        return new OffsetPaginatedResponse<>(true, "Operación correcta", result);
    }

    // Respuesta exitosa con paginación basada en cursores
    public static <T> CursorPaginatedResponse<List<T>> paginatedSuccessWithCursor(List<T> result, String nextCursor, String previousCursor, boolean hasNextPage, boolean hasPreviousPage) {
        return new CursorPaginatedResponse<>(true, "Operación correcta", result, nextCursor, previousCursor, hasNextPage, hasPreviousPage);
    }
}

