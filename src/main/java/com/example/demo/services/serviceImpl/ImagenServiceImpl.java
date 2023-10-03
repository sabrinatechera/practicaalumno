package com.example.demo.services.serviceImpl;

import com.example.demo.entities.Imagen;
import com.example.demo.repository.ImagenRepository;
import com.example.demo.services.ImagenService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImagenServiceImpl implements ImagenService {




    private final ImagenRepository imagenRepository;

    public ImagenServiceImpl(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    @Override
    public Imagen guardar(MultipartFile archivo){

        if (!archivo.isEmpty()){
            try{
                Imagen imagen = new Imagen();

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepository.save(imagen);

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }


    @Override
    public Optional<Imagen> obtenerImagenPorId(Long id) {
        return imagenRepository.findById(id);
    }

    @Override
    public void eliminarImagen(Long id) {
        imagenRepository.deleteById(id);
    }


}
