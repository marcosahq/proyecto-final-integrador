package com.app.hotel.samples.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SampleDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime fechaCreado;
    private LocalDateTime fechaActualizado;
}
