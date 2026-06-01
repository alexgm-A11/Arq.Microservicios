package com.examen.msgestiontaller.controller;

import com.examen.msgestiontaller.client.AlumnoClient;
import com.examen.msgestiontaller.client.InstructorClient;
import com.examen.msgestiontaller.model.Taller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/talleres")
public class TallerController {

    private final Map<Long, Taller> talleres = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Autowired
    private InstructorClient instructorClient;

    @Autowired
    private AlumnoClient alumnoClient;

    @GetMapping
    public ResponseEntity<List<Taller>> listar() {
        return ResponseEntity.ok(new ArrayList<>(talleres.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taller> obtener(@PathVariable Long id) {
        Taller taller = talleres.get(id);
        return taller != null ? ResponseEntity.ok(taller) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Taller taller) {
        // Validar que el instructor existe
        try {
            Boolean instructorExiste = instructorClient.verificarExistencia(taller.getInstructorId());
            if (!instructorExiste) {
                return ResponseEntity.badRequest()
                        .body("El instructor con ID " + taller.getInstructorId() + " no existe");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Error al validar instructor: " + e.getMessage());
        }

        // Validar que el alumno existe
        try {
            Boolean alumnoExiste = alumnoClient.verificarExistencia(taller.getAlumnoId());
            if (!alumnoExiste) {
                return ResponseEntity.badRequest()
                        .body("El alumno con ID " + taller.getAlumnoId() + " no existe");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Error al validar alumno: " + e.getMessage());
        }

        // Crear taller
        Long id = idGenerator.getAndIncrement();
        taller.setId(id);
        talleres.put(id, taller);

        return ResponseEntity.status(HttpStatus.CREATED).body(taller);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Taller taller) {
        if (!talleres.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        // Validar existencia (opcional, se puede omitir para actualización)
        taller.setId(id);
        talleres.put(id, taller);
        return ResponseEntity.ok(taller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (talleres.remove(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}