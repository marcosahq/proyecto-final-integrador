package com.app.hotel.personas.controller;

import com.app.hotel.personas.model.dto.PersonaDto;
import com.app.hotel.personas.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping("/page/{page}")
    public Page<PersonaDto> getAllPersonas(@PathVariable Integer page) {
        return personaService.findAllPersonas(PageRequest.of(page, 10));
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
