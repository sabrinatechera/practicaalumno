package com.example.demo.services;

import com.example.demo.entities.Curso;

import java.time.LocalDate;
import java.util.List;

public interface CursoService {


    void createCurso(String titulo, String descripcion, LocalDate fechaCreacion) throws Exception;

    List<Curso> findAllCursos();


    void updateCurso(String titulo, String descripcion, LocalDate fechaCreacion, Long id) throws Exception;

    void deleteCurso(Long id) throws Exception;

    Curso findById(Long cursoId);
}
