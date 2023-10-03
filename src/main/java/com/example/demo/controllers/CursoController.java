package com.example.demo.controllers;

import com.example.demo.entities.Curso;
import com.example.demo.services.AlumnoService;
import com.example.demo.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private  CursoService cursoService;

    @Autowired
    private AlumnoService alumnoService;




    @GetMapping("/listarcursos")// este funcion asi
    public String listarCursos(ModelMap model) {
        List<Curso> cursos = cursoService.findAllCursos();
        model.addAttribute("cursos", cursos);
        return "/listarCurso";

    }

    @GetMapping("/formulario-curso")
    public String mostrarFormularioCurso() {
        return "formularioCurso";
    }

   @PostMapping("/crear-curso")
    public String crearCurso(@RequestParam String titulo, @RequestParam String descripcion, LocalDate hora) throws Exception {
        cursoService.createCurso(titulo, descripcion,hora);

        return "redirect:/cursos";
    }


    @GetMapping("/mostrarCurso/{id}")// este funcion asi
    public String mostrarCurso(@PathVariable Long id, ModelMap model) {

        Curso curso= cursoService.findById(id);
        model.put("curso", curso);
        return "/mostarCurso";//este no existe aun

    }
}



