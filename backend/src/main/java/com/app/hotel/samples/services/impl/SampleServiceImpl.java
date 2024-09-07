package com.app.hotel.samples.services.impl;

import com.app.hotel.samples.models.dtos.SampleDto;
import com.app.hotel.samples.models.entities.Sample;
import com.app.hotel.samples.repositories.SampleRepository;
import com.app.hotel.samples.services.SampleService;
import com.app.hotel.samples.utils.SampleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {
    private final SampleRepository sampleRepository;
    private final SampleUtil sampleUtil;

    @Override
    public Page<SampleDto> findAllSamples(Pageable pageable) {
        return sampleRepository.findAll(pageable) // Leer de BD
                .map(sampleUtil::mapSampleToDto);
    }

    @Override
    public Optional<SampleDto> findSampleById(Long id) {
        return sampleRepository.findById(id) // Leer de BD
                .map(sampleUtil::mapSampleToDto);
    }

    @Override
    public Optional<SampleDto> saveSample(SampleDto sampleDto) {
        return Optional.ofNullable(sampleDto)
                .map(sampleUtil::mapSaveSample)
                .map(sampleRepository::save)// Guardar en BD
                .map(sampleUtil::mapSampleToDto);
    }

    @Override
    public Optional<SampleDto> updateSample(Long id, SampleDto sampleDto) {
        return sampleRepository.findById(id)
                .map(entity -> {
                    sampleUtil.mapUpdateSample(sampleDto, entity);
                    sampleRepository.save(entity); // Actualizar en BD
                    return sampleUtil.mapSampleToDto(entity);
                });
    }

    @Override
    public Optional<Boolean> deleteSample(Long id) {
        return sampleRepository.findById(id)
                .map(entity -> {
                    sampleRepository.deleteById(entity.getId()); // Eliminar en BD
                    return true; // Retorna true si fue eliminado
                });
    }
}
