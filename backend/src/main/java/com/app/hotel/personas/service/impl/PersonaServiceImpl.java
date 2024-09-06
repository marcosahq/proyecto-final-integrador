package com.app.hotel.personas.service.impl;

import com.app.hotel.personas.model.dto.PersonaDto;
import com.app.hotel.personas.model.entity.Persona;
import com.app.hotel.personas.repository.PersonaRepository;
import com.app.hotel.personas.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    @Override
    public Page<PersonaDto> findAllPersonas(Pageable pageable) {
        Page<Persona> personas = personaRepository.findAll(pageable);
        return personas.map(this::mapPersonaToDto);
    }

    @Override
    public Optional<PersonaDto> findPersonaById(Long id) {
        return personaRepository.findById(id).map(this::mapPersonaToDto);
    }

    @Override
    public PersonaDto savePersona(PersonaDto personaDto) {
        return Optional.ofNullable(personaDto)
                .map(this::mapDtoToPersona)
                .map(persona -> {
                    persona.setFechaCreado(LocalDateTime.now());
                    return persona;
                })
                .map(personaRepository::save)
                .map(this::mapPersonaToDto)
                .orElseThrow(() -> new IllegalArgumentException("No se pudo guardar la persona"));
    }

    @Override
    public PersonaDto updatePersona(Long id, PersonaDto personaDto) {
        return personaRepository.findById(id)
                .map(existingPersona -> {
                    mapUpdatePersona(personaDto, existingPersona);
                    existingPersona.setFechaActualizado(LocalDateTime.now());
                    return personaRepository.save(existingPersona);
                })
                .map(this::mapPersonaToDto)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró a la persona con ID: " + id));
    }

    @Override
    public void deletePersona(Long id) {
        Optional.of(id) // Envuelve el ID en un Optional
                .filter(personaRepository::existsById) // Filtra si la persona existe
                .map(idPersona ->{
                    personaRepository.deleteById(idPersona);
                    return idPersona;
                })
                .orElseThrow(() -> new EntityNotFoundException("No se encontró persona con el ID: " + id)); // Lanza excepción si no existe
    }

    private PersonaDto mapPersonaToDto(Persona persona) {
        return PersonaDto.builder()
                .id(persona.getId())
                .nombre(persona.getNombre())
                .apellido(persona.getApellido())
                .tipoDocumento(persona.getTipoDocumento())
                .nroDocumento(persona.getNroDocumento())
                .sexo(persona.getSexo())
                .edad(persona.getEdad())
                .telefono(persona.getTelefono())
                .fechaNacimiento(persona.getFechaNacimiento())
                .estadoCivil(persona.getEstadoCivil())
                .estado(persona.getEstado())
                .fechaCreado(persona.getFechaCreado())
                .fechaActualizado(persona.getFechaActualizado())
                .build();
    }

    private Persona mapDtoToPersona(PersonaDto personaDto) {
        Persona persona = new Persona();
        persona.setId(personaDto.getId());
        mapUpdatePersona(personaDto, persona);
        return persona;
    }

    private void mapUpdatePersona(PersonaDto personaDto, Persona existingPersona) {
        existingPersona.setNombre(personaDto.getNombre());
        existingPersona.setApellido(personaDto.getApellido());
        existingPersona.setTipoDocumento(personaDto.getTipoDocumento());
        existingPersona.setNroDocumento(personaDto.getNroDocumento());
        existingPersona.setSexo(personaDto.getSexo());
        existingPersona.setEdad(personaDto.getEdad());
        existingPersona.setTelefono(personaDto.getTelefono());
        existingPersona.setFechaNacimiento(personaDto.getFechaNacimiento());
        existingPersona.setEstadoCivil(personaDto.getEstadoCivil());
        existingPersona.setEstado(personaDto.getEstado());
    }
}
