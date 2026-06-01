package com.examen.msgestionalumno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGestionAlumnoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsGestionAlumnoApplication.class, args);
    }
}