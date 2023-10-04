package com.example.demo.controllers;

import com.example.demo.entities.Alumno;
import com.example.demo.entities.Curso;
import com.example.demo.entities.Imagen;
import com.example.demo.services.AlumnoService;
import com.example.demo.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class AlumnoController {

    private final AlumnoService alumnoService;
    private final CursoService cursoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService, CursoService cursoService) {
        this.alumnoService = alumnoService;
        this.cursoService = cursoService;
    }


    @GetMapping("/")//funciona te redirige al index cuando corres el sistema
    public String alumno(ModelMap modelo) {
        modelo.addAttribute("cursos", cursoService.findAllCursos());
        return "index.html";

    }


    @PostMapping("/createAlumno")
    public String agregarAlumno(@RequestParam String nombre, @RequestParam String apellido,
                                @RequestParam LocalDate fechaNacimiento, @RequestParam Long cursoId, ModelMap modelo, MultipartFile archivo) throws Exception {
        try {

            Curso curso = cursoService.findById(cursoId);
            modelo.addAttribute("cursos", cursoService.findAllCursos());
            alumnoService.createAlumno(nombre, apellido, fechaNacimiento, curso, archivo);
            // modelo.put("exito", "El alumno se ha creado exitosamente!");
            return "redirect:/index.html";

        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "index.html";

        }
    }

    @GetMapping("/listarAlumnos")//este esta bien. Muestraa http://localhost:8080/listarAlumnos
    public String listarAlumnos(ModelMap model) {
        List<Alumno> alumnos = alumnoService.findAllAlumnos();
        model.addAttribute("alumnos", alumnos);
        return "/listar";
    }


    @GetMapping("/mostrarAlumno/{id}")
    public String mostrarAlumno(@PathVariable Long id, ModelMap model) {

        Alumno alumnoencontrado = alumnoService.findById(id);
        model.put("alumno", alumnoencontrado);
        return "/mostarCurso";//este no existe aun

    }
    @GetMapping("/editar/{id}") //    aca mostramos la vista de edicioon de alumno

    public String mostrarFormularioEditarAlumno(@PathVariable Long id, ModelMap model) {
        Alumno alumno = alumnoService.findById(id);
        model.put("alumno", alumno);
        model.addAttribute("alumno", alumno);
        return "alumnos/editar";
    }
    
//    @GetMapping("/alumnosEnCurso/{cursoId}")
//    public String obtenerAlumnosEnCurso(@PathVariable Long cursoId, ModelMap model) {
//        List<Alumno> alumnosEnCurso = alumnoService.obtenerAlumnosEnCurso(cursoId);
//        model.addAttribute("alumnosEnCurso", alumnosEnCurso);
//        return "alumnos/alumnosEnCurso";
//    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id) {

        alumnoService.eliminarAlumnoPorId(id);
        // aca falta agregar mensaje de exito un mensaje de éxito
        return "redirect:/alumnos/listar";
    }

   /*
    @GetMapping("/buscar")
    public String buscarAlumnos(@RequestParam String nombreApellido, ModelMap model) {
        List<Alumno> alumnos = alumnoService.buscarAlumnosPorNombreOApellido(nombreApellido);
        model.addAttribute("alumnos", alumnos);
        return "alumnos/listar";
    }
@GetMapping("/agregar")
    public String mostrarFormularioAgregarAlumno(ModelMap model) {
        // Aquí puedes cargar datos necesarios, como la lista de cursos, para el formulario de agregar alumno
        return "alumnos/agregar";
    }
     @PostMapping("/editar")//este se ejecuta cuando se aprieta el boton de guardar edicion
    public String editarAlumno(@RequestParam Long id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam LocalDate fechaNacimiento, @RequestParam Long cursoId) {
        // Valida los datos y llama a editarAlumno en tu servicio
        // Maneja las excepciones aquí si es necesario
        // Después, redirige a la página de listado o muestra un mensaje de éxito
        return "redirect:/alumnos/listar";
    }

*/
}