package com.example.demo.controllers;

import com.example.demo.entities.Alumno;
import com.example.demo.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/imagen")
public class ImagenController {

    @Autowired
    private AlumnoService alumnoService;


    @GetMapping("/mostrarAlumno/{id}")
       public ResponseEntity<byte[]> imagenPersona(@PathVariable Long id) {

        Alumno alumno = alumnoService.findById(id);
        byte[] imagen = alumno.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);

    }

}
