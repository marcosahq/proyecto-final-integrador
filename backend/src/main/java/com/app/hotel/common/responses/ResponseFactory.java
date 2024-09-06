package com.app.hotel.common.responses;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseFactory<T> {
    // Getters y setters
    private boolean success;
    private String message;
    private T result;

    // Constructores
    public ResponseFactory(boolean success, String message, T result) {
        this.setSuccess(success);
        this.setMessage(message);
        this.setResult(result);
    }

    // Respuesta exitosa sin paginación
    public static <T> ResponseFactory<T> success(T result) {
        return new ResponseFactory<>(true, "Operación correcta", result);
    }

    // Respuesta exitosa con mensaje personalizado
    public static <T> ResponseFactory<T> success(String message, T result) {
        return new ResponseFactory<>(true, message, result);
    }

    // Respuesta de error con mensaje personalizado
    public static <T> ResponseFactory<T> error(String message) {
        return new ResponseFactory<>(false, message, null);
    }

    // Respuesta exitosa con paginación por Offset y Limit
    public static <T> OffsetPaginatedResponse<List<T>> paginatedSuccessWithOffset(List<T> result, long total, int perPage, int currentPage, String baseUrl) {
        return new OffsetPaginatedResponse<>(true, "Operación correcta", result, total, perPage, currentPage,baseUrl);
    }

    // Respuesta exitosa con paginación basada en cursores
    public static <T> CursorPaginatedResponse<List<T>> paginatedSuccessWithCursor(List<T> result, String nextCursor, String previousCursor, boolean hasNextPage, boolean hasPreviousPage) {
        return new CursorPaginatedResponse<>(true, "Operación correcta", result, nextCursor, previousCursor, hasNextPage, hasPreviousPage);
    }
}

