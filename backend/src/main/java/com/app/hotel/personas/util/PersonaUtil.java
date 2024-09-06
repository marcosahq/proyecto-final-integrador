package com.app.hotel.personas.util;

import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;

public class PersonaUtil {
    public static String getBaseUrl(@NotNull HttpServletRequest request) {
        // Obtiene el esquema (http o https)
        String scheme = request.getScheme();

        // Obtiene el nombre del servidor
        String serverName = request.getServerName();

        // Obtiene el puerto del servidor
        int serverPort = request.getServerPort();

        // Obtiene el contexto de la aplicación
        String contextPath = request.getContextPath();

        // Obtiene la URI y el query string completo
        String requestUri = request.getRequestURI();

        // Construye el baseUrl incluyendo el path completo
        return scheme + "://" + serverName + ":" + serverPort + contextPath + requestUri;
    }
}
