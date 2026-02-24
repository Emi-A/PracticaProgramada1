package com.practica_programada.controller;

import com.practica_programada.domain.Curso;
import com.practica_programada.service.CursoService;
import com.practica_programada.service.EstudianteService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService;
    private final EstudianteService estudianteService;

    public CursoController(CursoService cursoService, EstudianteService estudianteService) {
        this.cursoService = cursoService;
        this.estudianteService = estudianteService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);
        model.addAttribute("curso", new Curso()); // para formulario agregar
        
        // Totales generales
        model.addAttribute("totalCursos", cursoService.countCursos());
        model.addAttribute("totalEstudiantes", estudianteService.countEstudiantes());

        Map<Integer, Long> estPorCurso = new HashMap<>();
        for (Curso c : cursos) {
            estPorCurso.put(c.getIdCurso(), estudianteService.countEstudiantesPorCurso(c.getIdCurso()));
        }
        model.addAttribute("estPorCurso", estPorCurso);
        return "curso/listado";
    }

   @PostMapping("/guardar")
    public String guardar(Curso curso, @RequestParam("imagenFile") MultipartFile imagenFile) {
    cursoService.save(curso, imagenFile);
    return "redirect:/curso/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idCurso) {
        cursoService.delete(idCurso);
        return "redirect:/curso/listado";
    }

    @GetMapping("/modificar/{idCurso}")
    public String modificar(@PathVariable Integer idCurso, Model model) {
        Optional<Curso> cursoOpt = cursoService.getCurso(idCurso);
        if (cursoOpt.isEmpty()) {
            return "redirect:/curso/listado";
        }
        model.addAttribute("curso", cursoOpt.get());
        return "curso/modifica";
    }
}