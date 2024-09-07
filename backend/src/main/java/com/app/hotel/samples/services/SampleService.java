package com.app.hotel.samples.services;

import com.app.hotel.samples.models.dtos.SampleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SampleService {
    Page<SampleDto> findAllSamples(Pageable pageable);

    Optional<SampleDto> findSampleById(Long id);

    Optional<SampleDto> saveSample(SampleDto sampleDto);

    Optional<SampleDto> updateSample(Long id, SampleDto sampleDto);

    Optional<?> deleteSample(Long id);
}
