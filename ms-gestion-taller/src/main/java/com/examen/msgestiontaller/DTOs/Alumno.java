package com.examen.msgestiontaller.model;

import lombok.Data;

@Data
public class Alumno {
    private Long id;
    private String nombre;
    private String email;
    private String carrera;
    private Integer semestre;
}