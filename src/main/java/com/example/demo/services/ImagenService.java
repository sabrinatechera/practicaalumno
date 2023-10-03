package com.example.demo.services;

import com.example.demo.entities.Imagen;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


public interface ImagenService {


    Imagen guardar (MultipartFile archivo);

    Optional<Imagen> obtenerImagenPorId(Long id);

    void eliminarImagen(Long id);
}
