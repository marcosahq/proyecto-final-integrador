package com.app.hotel.personas.controller;

import com.app.hotel.common.requests.CustomRequest;
import com.app.hotel.common.responses.ResponseFactory;
import com.app.hotel.common.utils.RequestUtil;
import com.app.hotel.personas.model.dto.PersonaDto;
import com.app.hotel.personas.service.implementation.PersonaServiceImpl;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaServiceImpl personaService;

    @GetMapping()
    public ResponseEntity<?> getAllPersonas(HttpServletRequest request, @ModelAttribute CustomRequest<?> personaRequest) {
        if (personaRequest.getLimit() != null && personaRequest.getPage() != null) {
            int limit = Integer.parseInt(personaRequest.getLimit());
            int page = Integer.parseInt(personaRequest.getPage());

            Page<PersonaDto> personaPage = personaService.findAllPersonasPaginate(PageRequest.of(page - 1, limit));
            List<PersonaDto> result = personaPage.getContent();

            String baseUrl = RequestUtil.getBaseUrl(request);

            long total = personaPage.getTotalElements();
            return ResponseEntity.ok(ResponseFactory.withOffset(result, total, limit, page, baseUrl));
        } else {
            List<PersonaDto> result = personaService.findAllPersonas();
            ResponseFactory<List<PersonaDto>> response = ResponseFactory.success(result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> getPersonaById(@PathVariable Long id) {
        return personaService.findPersonaById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró a la persona con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<PersonaDto> createPersona(@RequestBody PersonaDto personaDto) {
        PersonaDto savedPersona = personaService.savePersona(personaDto);
        return new ResponseEntity<>(savedPersona, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> updatePersona(@PathVariable Long id, @RequestBody PersonaDto personaDto) {
        PersonaDto updatedPersona = personaService.updatePersona(id, personaDto);
        return ResponseEntity.ok(updatedPersona);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }
}
