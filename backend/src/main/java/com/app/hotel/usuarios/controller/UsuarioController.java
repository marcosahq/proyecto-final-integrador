package com.app.hotel.usuarios.controller;

import com.app.hotel.common.controllers.BaseController;
import com.app.hotel.common.requests.CustomRequest;
import com.app.hotel.common.responses.ResponseFactory;
import com.app.hotel.common.responses.ResultOffsetPagination;
import com.app.hotel.common.utils.RequestUtil;
import com.app.hotel.usuarios.model.dto.UsuarioDto;
import com.app.hotel.usuarios.service.implementation.UsuarioServiceImpl;
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
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController extends BaseController {

    private final UsuarioServiceImpl usuarioService;

    @GetMapping
    public ResponseEntity<?> getAllUsuarios(@ModelAttribute CustomRequest<?> personaRequest) {
        if (personaRequest.getLimit() != null && personaRequest.getPage() != null) {
            int limit = Integer.parseInt(personaRequest.getLimit());
            int page = Integer.parseInt(personaRequest.getPage());

            Page<UsuarioDto> usuariosPaginate = usuarioService.findAllUsuariosPaginate(PageRequest.of(page - 1, limit));
            List<UsuarioDto> result = usuariosPaginate.getContent();
            String baseUrl = RequestUtil.getBaseUrl(getHttpRequest());
            long total = usuariosPaginate.getTotalElements();

            ResponseFactory<ResultOffsetPagination<UsuarioDto>> response = ResponseFactory.withOffset(result, total, page, limit, baseUrl);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            List<UsuarioDto> result = usuarioService.findAllUsuarios();
            ResponseFactory<List<UsuarioDto>> response = ResponseFactory.success(result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }
}
