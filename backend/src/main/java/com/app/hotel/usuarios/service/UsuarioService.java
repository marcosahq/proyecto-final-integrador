package com.app.hotel.usuarios.service;

import com.app.hotel.samples.model.dto.SampleDto;
import com.app.hotel.usuarios.model.dto.UsuarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
    Page<SampleDto> findAllUsuarios(Pageable pageable);

    SampleDto findUsuarioById(Long id);

    SampleDto saveUsuario(UsuarioDto dto);

    SampleDto updateUsuario(Long id, UsuarioDto dto);

    void deleteUsuario(Long id);
}
