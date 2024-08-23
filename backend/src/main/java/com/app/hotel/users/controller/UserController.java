package com.app.hotel.users.controller;

import com.app.hotel.common.responses.ApiResponse;
import com.app.hotel.users.model.User;
import com.app.hotel.users.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ApiResponse.success(users); // Respuesta de éxito
    }

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ApiResponse.success("Usuario creado correctamente", createdUser);
        } catch (Exception e) {
            return ApiResponse.error("Error al crear usuario: " + e.getMessage()); // Respuesta de error
        }
    }
}
