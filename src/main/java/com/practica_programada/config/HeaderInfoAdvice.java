package com.practica_programada.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class HeaderInfoAdvice {

    @ModelAttribute
    public void addHeaderInfo(Model model) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        model.addAttribute("horaHeader", 
                LocalDateTime.now().format(fmt));
    }
}