package com.app.hotel.sandbox.controller;

import com.app.hotel.common.responses.ApiResponse;
import com.app.hotel.common.services.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class SandboxController {
    private final EmailService emailService;

    public SandboxController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/simple")
    public ApiResponse<Boolean> sendTestEmail() {
        try {
            emailService.sendSimpleMessage("test@example.com", "Test Subject", "This is a test email.");
            return ApiResponse.success(true);
        } catch (Exception e) {
            String message = "Error al enviar correo: " + e.getMessage();
            return ApiResponse.error(message);
        }
    }
}
