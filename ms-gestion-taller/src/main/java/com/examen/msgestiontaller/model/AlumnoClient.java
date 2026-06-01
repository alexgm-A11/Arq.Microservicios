package com.examen.msgestiontaller.client;

import com.examen.msgestiontaller.model.Alumno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-gestion-alumno")
public interface AlumnoClient {

    @GetMapping("/api/alumnos/{id}")
    ResponseEntity<Alumno> obtenerAlumno(@PathVariable("id") Long id);

    @GetMapping("/api/alumnos/{id}/existe")
    Boolean verificarExistencia(@PathVariable("id") Long id);
}