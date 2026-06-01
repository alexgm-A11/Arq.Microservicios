package com.examen.msgestiontaller.client;

import com.examen.msgestiontaller.model.Instructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-gestion-instructor")
public interface InstructorClient {

    @GetMapping("/api/instructores/{id}")
    ResponseEntity<Instructor> obtenerInstructor(@PathVariable("id") Long id);

    @GetMapping("/api/instructores/{id}/existe")
    Boolean verificarExistencia(@PathVariable("id") Long id);
}