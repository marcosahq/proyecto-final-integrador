package com.app.hotel.samples.controllers;

import com.app.hotel.common.requests.CustomRequest;
import com.app.hotel.common.responses.ResponseFactory;
import com.app.hotel.common.responses.ResultOffsetPagination;
import com.app.hotel.personas.util.PersonaUtil;
import com.app.hotel.samples.models.dtos.SampleDto;
import com.app.hotel.samples.services.impl.SampleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SampleController {

    private final SampleServiceImpl sampleService;

    @GetMapping
    public ResponseEntity<?> getAllSamples(HttpServletRequest httpRequest, @ModelAttribute CustomRequest<?> sampleRequest) {
        int limit = sampleRequest.getLimit();
        int page = sampleRequest.getPage();

        Page<SampleDto> sampleDtoPage = sampleService.findAllSamples(PageRequest.of(page - 1, limit));

        List<SampleDto> result = sampleDtoPage.getContent();
        String baseUrl = PersonaUtil.getBaseUrl(httpRequest);
        long total = sampleDtoPage.getTotalElements();

        ResponseFactory<ResultOffsetPagination<SampleDto>> response = ResponseFactory.paginatedSuccessWithOffset(result, total, limit, page, baseUrl);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSampleById(@PathVariable Long id) {
        SampleDto result = sampleService.findSampleById(id);

        ResponseFactory<SampleDto> response = ResponseFactory.success("Operación correcta", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSample(@RequestBody SampleDto sampleDto) {
        SampleDto result = sampleService.saveSample(sampleDto);

        ResponseFactory<SampleDto> response = ResponseFactory.success("Guardado correctamente", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSample(@PathVariable Long id, @RequestBody SampleDto sampleDto) {
        SampleDto result = sampleService.updateSample(id, sampleDto);

        ResponseFactory<SampleDto> response = ResponseFactory.success("Actualizado correctamente", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSample(@PathVariable Long id) {
        sampleService.deleteSample(id);

        ResponseFactory<Boolean> response = ResponseFactory.success("Eliminado correctamente", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
