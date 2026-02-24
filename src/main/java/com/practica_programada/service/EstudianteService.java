package com.practica_programada.service;

import com.practica_programada.domain.Estudiante;
import com.practica_programada.repository.EstudianteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> getEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> getEstudiante(Integer idEstudiante) {
        return estudianteRepository.findById(idEstudiante);
    }

    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public void delete(Integer idEstudiante) {
        estudianteRepository.deleteById(idEstudiante);
    }

    public List<Estudiante> getEstudiantesPorCurso(Integer idCurso) {
        return estudianteRepository.findByCursoIdCurso(idCurso);
    }
    
    public long countEstudiantes() {
        return estudianteRepository.count();
    }

    public long countEstudiantesPorCurso(Integer idCurso) {
        return estudianteRepository.countByCursoIdCurso(idCurso);
    }
}