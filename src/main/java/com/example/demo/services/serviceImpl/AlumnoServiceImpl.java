package com.example.demo.services.serviceImpl;

import com.example.demo.entities.Alumno;
import com.example.demo.entities.Curso;
import com.example.demo.entities.Imagen;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.services.AlumnoService;
import com.example.demo.services.ImagenService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {


    private final AlumnoRepository alumnoRepository;
    private final ImagenService imagenService;


    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, ImagenService imagenService) {
        this.alumnoRepository = alumnoRepository;
        this.imagenService = imagenService;
    }

    @Override
    public void createAlumno(String nombre, String apellido, LocalDate fechaNacimiento, Curso curso, MultipartFile archivo) throws Exception {

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
        Imagen imagen = imagenService.guardar(archivo);
        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setApellido(apellido);
        alumno.setFechaNacimiento(fechaNacimiento);
        alumno.setFechaInscripcion(LocalDate.now());
        alumno.setAprobado(false);
        alumno.setCurso(curso);
        alumno.setImagen(imagen);

        alumnoRepository.save(alumno);
    }

    @Override
    public List<Alumno> findAllAlumnos() {

        return alumnoRepository.findAll();
    }

    @Override
    public Alumno findById(Long id) {

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
