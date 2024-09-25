package com.app.hotel.samples.service;

import com.app.hotel.samples.model.dto.SampleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SampleService {
    List<SampleDto> findAllSamples();

    Page<SampleDto> findAllSamplesPaginate(Pageable pageable);

    SampleDto findSampleById(Long id);

    SampleDto saveSample(SampleDto sampleDto);

    SampleDto updateSample(Long id, SampleDto sampleDto);

    void deleteSample(Long id);
}
