package com.app.hotel.samples.controllers;

import com.app.hotel.common.requests.CustomRequest;
import com.app.hotel.common.responses.ResponseFactory;
import com.app.hotel.personas.util.PersonaUtil;
import com.app.hotel.samples.models.dtos.SampleDto;
import com.app.hotel.samples.services.impl.SampleServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<?> getAllSamples(HttpServletRequest request, @ModelAttribute CustomRequest<?> personaRequest) {
        int limit = personaRequest.getLimit();
        int page = personaRequest.getPage();

        Page<SampleDto> personaPage = sampleService.findAllSamples(PageRequest.of(page - 1, limit));
        List<SampleDto> result = personaPage.getContent();

        String baseUrl = PersonaUtil.getBaseUrl(request);
        long total = personaPage.getTotalElements();

        return ResponseEntity.ok(ResponseFactory.paginatedSuccessWithOffset(result, total, limit, page, baseUrl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSampleById(@PathVariable Long id) {
        SampleDto result = sampleService.findSampleById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró el ID: " + id));

        String message = "Operación Correcta";
        return ResponseEntity.ok(ResponseFactory.success(message, result));
    }

    @PostMapping
    public ResponseEntity<?> createSample(@RequestBody SampleDto sampleDto) {
        SampleDto result = sampleService.saveSample(sampleDto).orElseThrow(() -> new IllegalArgumentException("Error al guardar"));

        String message = "Guardado correctamente";
        return ResponseEntity.ok(ResponseFactory.success(message, result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSample(@PathVariable Long id, @RequestBody SampleDto sampleDto) {
        SampleDto result = sampleService.updateSample(id, sampleDto).orElseThrow(() -> new EntityNotFoundException("No se encontró el ID: " + id));

        String message = "Actualizado correctamente";
        return ResponseEntity.ok(ResponseFactory.success(message, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSample(@PathVariable Long id) {
        Boolean result = sampleService.deleteSample(id).orElseThrow(() -> new EntityNotFoundException("No se encontró el ID: " + id));

        String message = "Eliminado correctamente";
        return ResponseEntity.ok(ResponseFactory.success(message, result));
    }
}
