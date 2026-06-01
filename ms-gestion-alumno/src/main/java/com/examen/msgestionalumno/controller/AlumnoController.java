package com.examen.msgestionalumno.controller;

import com.examen.msgestionalumno.model.Alumno;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final Map<Long, Alumno> alumnos = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<Alumno>> listar() {
        return ResponseEntity.ok(new ArrayList<>(alumnos.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtener(@PathVariable Long id) {
        Alumno alumno = alumnos.get(id);
        return alumno != null ? ResponseEntity.ok(alumno) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Alumno> crear(@Valid @RequestBody Alumno alumno) {
        Long id = idGenerator.getAndIncrement();
        alumno.setId(id);
        alumnos.put(id, alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizar(@PathVariable Long id, @Valid @RequestBody Alumno alumno) {
        if (!alumnos.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        alumno.setId(id);
        alumnos.put(id, alumno);
        return ResponseEntity.ok(alumno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (alumnos.remove(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // Endpoint para verificar existencia (para el servicio de talleres)
    @GetMapping("/{id}/existe")
    public Boolean existe(@PathVariable Long id) {
        return alumnos.containsKey(id);
    }
}