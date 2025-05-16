package com.ProyectoMaquillaje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerHTML {

    @GetMapping("/")
    public String mostrarIndex() {
        return "index"; 
    }
    @GetMapping("/param1")
    public String mostrarParam1() {
        return "param1"; 
    }

    @GetMapping("/param2")
    public String mostrarParam2() {
        return "param2"; 
    }

    @GetMapping("/param3")
    public String mostrarParam3() {
        return "param3"; 
    }

    /* CAMBIAR ACA PARA QUIZ SELECT
     * @GetMapping("/quiz-select")
    public String mostrarQuizSelect() {
    return "quiz-select";
}
     */
}