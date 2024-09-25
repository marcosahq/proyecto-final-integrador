package com.app.hotel.samples.service;

import com.app.hotel.samples.model.dto.SampleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SampleService {
    Page<SampleDto> findAllSamples(Pageable pageable);

    SampleDto findSampleById(Long id);

    SampleDto saveSample(SampleDto sampleDto);

    SampleDto updateSample(Long id, SampleDto sampleDto);

    void deleteSample(Long id);
}
