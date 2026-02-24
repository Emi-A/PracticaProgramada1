package com.practica_programada.controller;

import com.practica_programada.domain.Estudiante;
import com.practica_programada.service.CursoService;
import com.practica_programada.service.EstudianteService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final CursoService cursoService;

    public EstudianteController(EstudianteService estudianteService, CursoService cursoService) {
        this.estudianteService = estudianteService;
        this.cursoService = cursoService;
    }

    @GetMapping("/listado")
    public String listado(@RequestParam(required = false) Integer idCurso, Model model) {

        var cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);

        var estudiantes = (idCurso == null)
                ? estudianteService.getEstudiantes()
                : estudianteService.getEstudiantesPorCurso(idCurso);

        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("estudiante", new Estudiante());

        // Totales
        model.addAttribute("totalCursos", cursos.size());
        model.addAttribute("totalEstudiantes", estudianteService.countEstudiantes());
        model.addAttribute("totalEstudiantesFiltrados", estudiantes.size());

        model.addAttribute("idCursoSeleccionado", idCurso);
        model.addAttribute("totalCursos", cursoService.countCursos());
        return "estudiante/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Estudiante estudiante) {
        
        estudianteService.save(estudiante);
        return "redirect:/estudiante/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idEstudiante) {
        estudianteService.delete(idEstudiante);
        return "redirect:/estudiante/listado";
    }

    @GetMapping("/modificar/{idEstudiante}")
    public String modificar(@PathVariable Integer idEstudiante, Model model) {
        Optional<Estudiante> estOpt = estudianteService.getEstudiante(idEstudiante);
        if (estOpt.isEmpty()) {
            return "redirect:/estudiante/listado";
        }

        var cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);

        model.addAttribute("estudiante", estOpt.get());
        return "estudiante/modifica";
    }
}