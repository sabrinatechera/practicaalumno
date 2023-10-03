package com.example.demo.services.serviceImpl;


import com.example.demo.entities.Curso;
import com.example.demo.repository.CursoRepository;
import com.example.demo.services.CursoService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {


    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {

        this.cursoRepository = cursoRepository;
    }
    @Override
    public void createCurso(String titulo, String descripcion, LocalDate fechaCreacion) throws Exception {


        if (titulo == null || titulo.length() <= 5) {
            throw new Exception("El título debe ser mayor a 5 caracteres y no estar vacío");
        }
        if (descripcion == null || descripcion.length() <= 5) {
            throw new Exception("La descripción debe ser mayor a 5 caracteres y no estar vacía");
        }

        Curso cursoExistente = cursoRepository.findByTitulo(titulo);
        if (cursoExistente != null) {
            throw new Exception("Ya existe un curso con el mismo título");
        }

        Curso curso = new Curso(titulo, descripcion, fechaCreacion != null ? fechaCreacion : LocalDate.now());
        cursoRepository.save(curso);
    }

    @Override
    public List<Curso> findAllCursos() {

        return cursoRepository.findAll();
    }

    @Override
    public void updateCurso(String titulo, String descripcion, LocalDate fechaCreacion, Long id) throws Exception {

        Optional<Curso> optionalCurso = cursoRepository.findById(id);

        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();

            if (titulo.length() >= 5) {
                curso.setTitulo(titulo);
            } else {
                throw new Exception("El título debe ser mayor a 5 caracteres y no estar vacío");
            }

            if (descripcion.length() >= 5) {
                curso.setDescripcion(descripcion);
            } else {
                throw new Exception("La descripción debe ser mayor a 5 caracteres y no estar vacía");
            }

            cursoRepository.save(curso);
        } else {
            throw new Exception("Curso no encontrado con ID: " + id);
        }


    }

    @Override
    public void deleteCurso(Long id) throws Exception {

        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } else {
            throw new Exception(" no existe el curso con el id " + id);
        }


    }
//    @Override
//    public Curso buscarCursoPorTitulo(String titulo) {
//        return cursoRepository.findByTitulo(titulo);
//    }

    @Override
    public Curso findById(Long cursoId) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        return cursoOptional.orElseThrow(() -> new NoSuchElementException("No se encontró el curso con el ID: " + cursoId));
    }

}



