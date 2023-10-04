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


    private  CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/listarcursos")// este funcion asi http://localhost:8080/curso/listarcursos
    public String listarCursos(ModelMap model) {
        List<Curso> cursos = cursoService.findAllCursos();
        model.addAttribute("cursos", cursos);
        return "/listarCurso";

    }


    @GetMapping("/cursoForm")
    public String crearCursoForm(ModelMap modelo) {
        modelo.addAttribute("cursos", cursoService.findAllCursos());
        return "/cursos";
    }

    @PostMapping("/crearCurso")
    public String crearCurso(String titulo, String descripcion, LocalDate fechaCreacion) {
        try {
            cursoService.createCurso(titulo, descripcion,fechaCreacion);
          //  model.addAttribute("exito", "Curso creado exitosamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            //model.addAttribute("error", e.getMessage());
     //   }
        return "redirect:/curso/cursoForm";
    }

    @GetMapping("/mostrarCurso/{id}")
    public String mostrarCurso(@PathVariable Long id, ModelMap model) {

        Curso curso= cursoService.findById(id);
        model.put("curso", curso);
        return "/mostarCurso";//este no existe aun

    }





}



