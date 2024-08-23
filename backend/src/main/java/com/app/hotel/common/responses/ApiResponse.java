package com.app.hotel.common.responses;

/*
    Explicación:
    success: Indica si la operación fue exitosa (true) o fallida (false).
    message: Contiene un mensaje que puede ser de éxito o error.
    data: Contiene la información que se devuelve en caso de una respuesta exitosa. Puede ser de cualquier tipo genérico (T), lo que lo hace flexible.
 */

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T result;

    // Constructores
    public ApiResponse(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters y setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    // Respuesta exitosa
    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse<>(true, "Operación correcta", result);
    }

    // Respuesta exitosa con mensaje personalizado
    public static <T> ApiResponse<T> success(String message, T result) {
        return new ApiResponse<>(true, message, result);
    }

    // Respuesta de error con mensaje personalizado
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
