package com.practica_programada.service;

import com.practica_programada.domain.Curso;
import com.practica_programada.repository.CursoRepository;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final FirebaseStorageService firebaseStorageService;

    public CursoService(CursoRepository cursoRepository, FirebaseStorageService firebaseStorageService) {
    this.cursoRepository = cursoRepository;
    this.firebaseStorageService = firebaseStorageService;
}

    public List<Curso> getCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> getCurso(Integer idCurso) {
        return cursoRepository.findById(idCurso);
    }

    @Transactional
    public Curso save(Curso curso, MultipartFile imagenFile) {
        curso = cursoRepository.save(curso);

        if (imagenFile == null || imagenFile.isEmpty()) {
            return curso;
        }

        try {
            String rutaImagen = firebaseStorageService.uploadImage(imagenFile, "curso", curso.getIdCurso());
            curso.setRutaImagen(rutaImagen);
            return cursoRepository.save(curso);
        } catch (IOException e) {
            return curso;
        }
    }

    public void delete(Integer idCurso) {
        cursoRepository.deleteById(idCurso);
    }
    public long countCursos() {
        return cursoRepository.count();
    }
}