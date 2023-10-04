package com.example.demo.controllers;

import com.example.demo.entities.Curso;
import com.example.demo.services.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/curso")
public class CursoController {


    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/listarcursos")// este funciona asi http://localhost:8080/curso/listarcursos
    public String listarCursos(ModelMap model) {
        List<Curso> cursos = cursoService.findAllCursos();
        model.addAttribute("cursos", cursos);
        return "/listarCurso";

    }


    @GetMapping("/curso")
    public String crearCursoForm(ModelMap modelo) {
        modelo.addAttribute("cursos", cursoService.findAllCursos());
        return "/cursos";
    }

    @PostMapping("/crearCurso")
    public String crearCurso(@RequestParam String titulo,@RequestParam  String descripcion, LocalDate fechaCreacion, ModelMap modelo) {
        try {
            cursoService.createCurso(titulo, descripcion, fechaCreacion);
            modelo.addAttribute("cursos", cursoService.findAllCursos());
            modelo.put("exito", "Curso creado exitosamente");
            return "redirect:/curso/cursoForm";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.addAttribute("cursos", cursoService.findAllCursos());
            return "redirect:/curso/cursoForm";
        }
    }

    @GetMapping("/mostrarCurso/{id}")
    public String mostrarCurso(@PathVariable Long id, ModelMap model) {

        Curso curso = cursoService.findById(id);
        model.put("curso", curso);
        return "/mostarCurso";//este no existe aun

    }


}



