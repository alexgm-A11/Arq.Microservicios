package com.examen.msadminconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer  // ← Esta anotación es CRUCIAL
public class MsAdminConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsAdminConfigServerApplication.class, args);
    }
}