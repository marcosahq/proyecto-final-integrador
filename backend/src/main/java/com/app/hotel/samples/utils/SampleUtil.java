package com.app.hotel.samples.utils;

import com.app.hotel.samples.models.dtos.SampleDto;
import com.app.hotel.samples.models.entities.Sample;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@NoArgsConstructor
public class SampleUtil {

    public SampleDto mapSampleToDto(Sample entity) {
        SampleDto.SampleDtoBuilder sampleDtoBuilder = SampleDto.builder();

        sampleDtoBuilder.id(entity.getId());
        sampleDtoBuilder.title(entity.getTitle());
        sampleDtoBuilder.description(entity.getDescription());
        sampleDtoBuilder.fechaCreado(entity.getFechaCreado());
        sampleDtoBuilder.fechaActualizado(entity.getFechaActualizado());

        return sampleDtoBuilder.build();
    }

    public Sample mapSaveSample(SampleDto sampleDto) {
        Sample entity = new Sample();

        entity.setId(sampleDto.getId());
        setEntity(sampleDto, entity);
        entity.setFechaCreado(LocalDateTime.now());

        return entity;
    }

    public void mapUpdateSample(SampleDto sampleDto, Sample entity) {
        setEntity(sampleDto, entity);
        entity.setFechaActualizado(LocalDateTime.now());
    }

    private void setEntity(SampleDto sampleDto, Sample entity) {
        entity.setTitle(sampleDto.getTitle());
        entity.setDescription(sampleDto.getDescription());
    }
}
