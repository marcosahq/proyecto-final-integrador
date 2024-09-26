package com.app.hotel.Empleados.controller;

import com.app.hotel.common.controllers.BaseController;
import com.app.hotel.common.requests.CustomRequest;
import com.app.hotel.common.responses.ResponseFactory;
import com.app.hotel.common.responses.ResultOffsetPagination;
import com.app.hotel.common.utils.RequestUtil;
import com.app.hotel.Empleados.model.dto.EmpleadosDto;
import com.app.hotel.Empleados.service.EmpleadosServiceImpl;
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
@RequestMapping("/empleados")
@RequiredArgsConstructor

public class EmpleadosController extends BaseController {

    private final EmpleadosServiceImpl empleadosService;

    @GetMapping
    public ResponseEntity<?> obtenerEmpleados(@ModelAttribute CustomRequest<?> personaRequest) {
        if (personaRequest.getLimit() != null && personaRequest.getPage() != null) {
            int limit = Integer.parseInt(personaRequest.getLimit());
            int page = Integer.parseInt(personaRequest.getPage());

            Page<EmpleadosDto> empladosDtoPage = empleadosService.paginar(PageRequest.of(page - 1, limit));

            List<EmpleadosDto> result = empleadosDtoPage.getContent();
            String baseUrl = RequestUtil.getBaseUrl(getHttpRequest());
            long total = empleadosDtoPage.getTotalElements();

            ResponseFactory<ResultOffsetPagination<EmpleadosDto>> response = ResponseFactory.withOffset(result, total, limit, page, baseUrl);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            List<EmpleadosDto> result = empleadosService.listar();
            ResponseFactory<List<EmpleadosDto>> response = ResponseFactory.success(result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

     @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEmpleadosPorId(@PathVariable Long id) {
        EmpleadosDto result = empleadosService.obtenerPorId(id);

        ResponseFactory<EmpleadosDto> response = ResponseFactory.success("Operación correcta", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearEmpleados(@RequestBody EmpleadosDto empleadosDto) {
        EmpleadosDto result = empleadosService.guardar(empleadosDto);

        ResponseFactory<EmpleadosDto> response = ResponseFactory.success("Guardado correctamente", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleados(@PathVariable Long id, @RequestBody EmpleadosDto empleadosDto) {
        EmpleadosDto result = empleadosService.actualizar(id, empleadosDto);

        ResponseFactory<EmpleadosDto> response = ResponseFactory.success("Actualizado correctamente", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpleados(@PathVariable Long id) {
        empleadosService.eliminar(id);

        ResponseFactory<Boolean> response = ResponseFactory.success("Eliminado correctamente", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}   