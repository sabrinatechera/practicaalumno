package com.example.demo.services.serviceImpl;

import com.example.demo.entities.Alumno;
import com.example.demo.entities.Curso;
import com.example.demo.entities.Imagen;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.ImagenRepository;
import com.example.demo.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final ImagenRepository imagenRepository;


    @Autowired
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, ImagenRepository imagenRepository) {
        this.alumnoRepository = alumnoRepository;
        this.imagenRepository = imagenRepository;
    }


    @Override
    public void createAlumno(String nombre, String apellido, LocalDate fechaNacimiento, Curso curso, Imagen imagen) throws Exception {

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("El nombre del alumno es obligatorio.");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("El apellido del alumno es obligatorio.");
        }

        if (fechaNacimiento == null) {
            throw new Exception("La fecha de nacimiento del alumno es obligatoria.");
        }

        if (curso == null) {
            throw new Exception("El curso es obligatorio para inscribir al alumno.");
        }

        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setApellido(apellido);
        alumno.setFechaNacimiento(fechaNacimiento);
        alumno.setFechaInscripcion( LocalDate.now());
        alumno.setAprobado(false);
        alumno.setCurso(curso);
        if (imagen != null) {
            imagenRepository.save(imagen);
            alumno.setImagen(imagen);
        }
        alumnoRepository.save(alumno);
    }

    @Override
    public List<Alumno> findAllAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno obtenerAlumnoPorId(Long id) {
        return alumnoRepository.findById(id).orElse(null);
    }


    @Override

    public void editarAlumno(Long id, String nombre, String apellido, LocalDate fechaNacimiento, Curso curso) {
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno != null) {
            alumno.setNombre(nombre);
            alumno.setApellido(apellido);
            alumno.setFechaNacimiento(fechaNacimiento);
            alumno.setCurso(curso);
            alumnoRepository.save(alumno);
        }
    }

    @Override
    public void eliminarAlumnoPorId(Long id) {
        alumnoRepository.deleteById(id);
    }
    @Override
    public void aprobarAlumno(Long id, boolean aprobado) {
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno != null) {
            alumno.setAprobado(aprobado);
            alumnoRepository.save(alumno);
        }
    }
    @Override
    // Buscar alumnos por nombre o apellido??
    public List<Alumno> buscarAlumnosPorNombreOApellido(String nombreApellido) {
        return alumnoRepository.findByNombreContainingOrApellidoContaining(nombreApellido, nombreApellido);
    }

//    @Override  // no se si esta bien
//    public List<Alumno> obtenerAlumnosEnCurso(Long cursoId) {
//
//        List<Alumno> listaAlumnoEnCurso=  alumnoRepository.findAllByCursoId(cursoId);
//
//        if (listaAlumnoEnCurso != null) {
//            return listaAlumnoEnCurso;
//        }
//        return new ArrayList<>(); // Si el curso no existe, devuelve una lista vacía.
//    }


}
