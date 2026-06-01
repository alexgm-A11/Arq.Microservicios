package com.examen.msgestiontaller.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "talleres")
@Data
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotNull
    @Column(name = "instructor_id")
    private Long instructorId;

    @NotNull
    @Column(name = "alumno_id")
    private Long alumnoId;

    private String estado = "PENDIENTE";
}