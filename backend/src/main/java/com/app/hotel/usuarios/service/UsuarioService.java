package com.app.hotel.usuarios.service;

import com.app.hotel.samples.model.dto.SampleDto;
import com.app.hotel.usuarios.model.dto.UsuarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    List<UsuarioDto> findAllUsuarios();

    Page<UsuarioDto> findAllUsuariosPaginate(Pageable pageable);

    UsuarioDto findUsuarioById(Long id);

    UsuarioDto saveUsuario(UsuarioDto dto);

    UsuarioDto updateUsuario(Long id, UsuarioDto dto);

    void deleteUsuario(Long id);
}
