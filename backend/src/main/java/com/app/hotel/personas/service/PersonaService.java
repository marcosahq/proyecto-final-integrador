package com.app.hotel.personas.service;

import com.app.hotel.personas.model.dto.PersonaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PersonaService {
    Page<PersonaDto> findAllPersonas(Pageable pageable);
    Optional<PersonaDto> findPersonaById(Long id);
    PersonaDto savePersona(PersonaDto personaDto);
    PersonaDto updatePersona(Long id, PersonaDto personaDto);
    void deletePersona(Long id);
}
