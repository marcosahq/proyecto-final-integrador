package com.app.hotel.common.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomRequest<T> {
    private Integer page = 1;  // Valor predeterminado de 1 para la página
    private Integer limit = 10; // Valor predeterminado de 10 para el límite
    private T entity;
}
