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


    @GetMapping("/")//funciona
    public String alumno(ModelMap modelo) {
        modelo.addAttribute("cursos", cursoService.findAllCursos());
        return "index.html";

    }


    @PostMapping("/createAlumnos")
    public String agregarAlumno(@RequestParam String nombre, @RequestParam String apellido,
                                @RequestParam LocalDate fechaNacimiento, @RequestParam Long cursoId,
                                @RequestParam Imagen imagen, ModelMap modelo) throws Exception {
        try {

            Curso curso = cursoService.findById(cursoId);// busca el curso pasado por id desde el front
            modelo.addAttribute("cursos", cursoService.findAllCursos());
            alumnoService.createAlumno(nombre, apellido, fechaNacimiento, curso, imagen);
            modelo.put("exito", "El alumno se ha creado exitosamente!");
            return "redirect:/index.html";

        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "index.html";

        }
    }

    @GetMapping("/listarAlumnos")//este esta bien. Muestra todo los alumnos
    public String  listarAlumnos(ModelMap model) {
        List<Alumno> alumnos = alumnoService.findAllAlumnos();
       model.addAttribute("alumnos", alumnos);
        return "/listar" ;
    }


    @PostMapping("/editar")//este se ejecuta cuando se aprieta el boton de guardar edicion
    public String editarAlumno(@RequestParam Long id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam LocalDate fechaNacimiento, @RequestParam Long cursoId) {
        // Valida los datos y llama a editarAlumno en tu servicio
        // Maneja las excepciones aquí si es necesario
        // Después, redirige a la página de listado o muestra un mensaje de éxito
        return "redirect:/alumnos/listar";
    }

    @GetMapping("/editar/{id}") //    aca mostramos la vista de edicioon de alumno

    public String mostrarFormularioEditarAlumno(@PathVariable Long id, ModelMap model) {
        Alumno alumno = alumnoService.obtenerAlumnoPorId(id);
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

   /* @GetMapping("/formulario-alumno")
    public String mostrarFormularioAlumno(ModelMap model) {
        List<Curso> cursos = cursoService.listarTodosLosCursos();
        model.addAttribute("cursos", cursos);
        return "formularioAlumno";
    }
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

*/
}