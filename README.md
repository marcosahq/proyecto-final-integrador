# proyecto-final-integrador

## Guia Videotutorial

https://www.youtube.com/watch?v=M7lhQMzzHWU

## Requerimientos

- Java JDK 17
- Laragon + MySQL
- Intellij Idea o Visual Studio Code

## Pasos para ejecutar en local

```sh
# [Paso 01]
# Ejecutar script db_hotel.sql 
```

```sh
# [Paso 02]
# Path: src/main/resources/application.properties
# Configurar con estos datos

spring.application.name=hotel
server.port=8000
spring.datasource.url=jdbc:mysql://localhost:3306/db_hotel
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

```sh
# [Paso 03]
# Path: src/main/java/com/app/hotel/HotelApplication.java
# Clic derecho > Ejecutar run HotelApplication.java

package com.app.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

}
```

