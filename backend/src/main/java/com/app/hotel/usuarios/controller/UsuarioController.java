package com.app.hotel.usuarios.controller;

import com.app.hotel.common.controllers.BaseController;
import com.app.hotel.common.requests.CustomRequest;
import com.app.hotel.common.responses.ResponseFactory;
import com.app.hotel.common.responses.ResultOffsetPagination;
import com.app.hotel.common.utils.RequestUtil;
import com.app.hotel.samples.model.dto.SampleDto;
import com.app.hotel.usuarios.model.dto.UsuarioDto;
import com.app.hotel.usuarios.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
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

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> getAllUsuarios(@ModelAttribute CustomRequest<?> personaRequest) {
        int limit = personaRequest.getLimit();
        int page = personaRequest.getPage();

        Page<UsuarioDto> usuarioDtoPage = usuarioService.findAllUsuariosPaginate(PageRequest.of(page - 1, limit));
        List<UsuarioDto> result = usuarioDtoPage.getContent();
        String baseUrl = RequestUtil.getBaseUrl(getHttpRequest());
        long total = usuarioDtoPage.getTotalElements();

        ResponseFactory<ResultOffsetPagination<UsuarioDto>> response = ResponseFactory.withOffset(result, total, limit, page, baseUrl);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
