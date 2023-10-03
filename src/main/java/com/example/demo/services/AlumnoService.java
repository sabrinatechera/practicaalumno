package com.example.demo.services;

import com.example.demo.entities.Alumno;
import com.example.demo.entities.Curso;
import com.example.demo.entities.Imagen;
import java.time.LocalDate;
import java.util.List;

public interface AlumnoService {







    void createAlumno(String nombre, String apellido, LocalDate fechaNacimiento, Curso curso, Imagen imagen) throws Exception;

    List<Alumno> findAllAlumnos();

    // Obtener un alumno por su ID
    Alumno obtenerAlumnoPorId(Long id);


    // Editar un alumno
    void editarAlumno(Long id, String nombre, String apellido, LocalDate fechaNacimiento, Curso curso);

    // Eliminar un alumno por su ID
    void eliminarAlumnoPorId(Long id);

    // Aprobar o desaprobar un alumno
    void aprobarAlumno(Long id, boolean aprobado);


    // Buscar alumnos por nombre o apellido??
    List<Alumno> buscarAlumnosPorNombreOApellido(String nombreApellido);

}