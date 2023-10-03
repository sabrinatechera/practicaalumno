package com.example.demo.repository;

import com.example.demo.entities.Alumno;
import com.example.demo.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno,Long > {


    List<Alumno> findByNombreContainingOrApellidoContaining(String nombreApellido, String nombreApellido1);

    List<Alumno> findByCurso(Curso curso);


    @Query("SELECT a FROM Alumno a WHERE a.curso.id = :cursoId")
    List<Alumno> buscarAlumnosPorCurso(@Param("cursoId") Long id);
}
