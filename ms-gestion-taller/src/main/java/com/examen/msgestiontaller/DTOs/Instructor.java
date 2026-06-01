package com.examen.msgestiontaller.model;

import lombok.Data;

@Data
public class Instructor {
    private Long id;
    private String nombre;
    private String email;
    private String especialidad;
    private Integer aniosExperiencia;
}