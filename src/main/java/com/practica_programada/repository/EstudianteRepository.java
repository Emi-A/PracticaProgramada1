package com.practica_programada.repository;

import com.practica_programada.domain.Estudiante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    List<Estudiante> findByCursoIdCurso(Integer idCurso);
    long countByCursoIdCurso(Integer idCurso);
}
