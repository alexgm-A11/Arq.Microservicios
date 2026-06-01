package com.examen.msgestioninstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGestionInstructorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsGestionInstructorApplication.class, args);
    }
}