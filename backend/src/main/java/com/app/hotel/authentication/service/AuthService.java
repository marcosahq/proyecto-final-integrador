package com.app.hotel.authentication.service;

import com.app.hotel.usuarios.model.dto.UsuarioDto;

public interface AuthService {
    UsuarioDto login(String username, String password);

    Boolean logout();
}
