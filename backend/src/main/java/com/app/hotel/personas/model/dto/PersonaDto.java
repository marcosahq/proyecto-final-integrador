package com.app.hotel.personas.model.dto;

import com.app.hotel.personas.model.entity.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private String telefono;
    private String correo;
    private Estado estado;
    private LocalDateTime fechaCreado;
    private LocalDateTime fechaActualizado;
}
