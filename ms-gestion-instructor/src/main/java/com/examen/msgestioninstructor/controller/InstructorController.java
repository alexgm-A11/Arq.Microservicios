package com.examen.msgestioninstructor.controller;

import com.examen.msgestioninstructor.model.Instructor;
import com.examen.msgestioninstructor.repository.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructores")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping
    public List<Instructor> listar() {
        return instructorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> obtener(@PathVariable Long id) {
        return instructorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Instructor crear(@Valid @RequestBody Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> actualizar(@PathVariable Long id, @Valid @RequestBody Instructor instructor) {
        if (!instructorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        instructor.setId(id);
        return ResponseEntity.ok(instructorRepository.save(instructor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!instructorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        instructorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/existe")
    public Boolean existe(@PathVariable Long id) {
        return instructorRepository.existsById(id);
    }
}