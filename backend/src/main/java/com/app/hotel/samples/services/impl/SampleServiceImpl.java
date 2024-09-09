package com.app.hotel.samples.services.impl;

import com.app.hotel.samples.exceptions.SampleNotFoundException;
import com.app.hotel.samples.models.dtos.SampleDto;
import com.app.hotel.samples.models.entities.Sample;
import com.app.hotel.samples.repositories.SampleRepository;
import com.app.hotel.samples.services.SampleService;
import com.app.hotel.samples.services.helpers.SampleServiceHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {
    private final SampleRepository sampleRepository;
    private final SampleServiceHelper sampleServiceHelper;

    @Override
    public Page<SampleDto> findAllSamples(Pageable pageable) {
        Page<Sample> entityPage = sampleRepository.findAll(pageable); // Leer la lista de entidades de la base de datos con paginación
        return entityPage.map(sampleServiceHelper::mapSampleToDto); // Mapear las entidades a DTOs y devolver la página resultante
    }

    @Override
    public SampleDto findSampleById(Long id) {
        Sample entity = sampleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró el ID: " + id));
        return sampleServiceHelper.mapSampleToDto(entity); // Mapear la entidad a DTO y devolver
    }

    @Override
    @Transactional
    public SampleDto saveSample(SampleDto sampleDto) {
        if (sampleDto == null) throw new IllegalArgumentException("El DTO no puede ser nulo");
        Sample entity = sampleServiceHelper.mapSaveSample(sampleDto); // Mapear el DTO a una entidad
        Sample savedEntity = sampleRepository.save(entity); // Guardar la entidad en la base de datos
        return sampleServiceHelper.mapSampleToDto(savedEntity); // Mapear la entidad guardada al DTO y devolverlo
    }

    @Override
    @Transactional
    public SampleDto updateSample(Long id, SampleDto sampleDto) {
        Sample entity = sampleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró el ID: " + id));
        sampleServiceHelper.mapUpdateSample(sampleDto, entity); // Lógica para actualizar la entidad con los datos del DTO
        Sample updatedEntity = sampleRepository.save(entity); // Guardar la entidad actualizada en la base de datos
        return sampleServiceHelper.mapSampleToDto(updatedEntity); // Lógica para mapear la entidad al DTO
    }

    @Override
    @Transactional
    public void deleteSample(Long id) {
        Sample entity = sampleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró el ID: " + id));
        sampleRepository.deleteById(entity.getId()); // Eliminar
    }
}
