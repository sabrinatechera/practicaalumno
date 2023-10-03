package com.example.demo.controllers;


import com.example.demo.entities.Alumno;
import com.example.demo.entities.Curso;
import com.example.demo.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/")
public class CursoController {
    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

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



}



