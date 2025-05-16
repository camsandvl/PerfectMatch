package com.ProyectoMaquillaje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerHTML {

    @GetMapping("/")
    public String mostrarIndex() {
        return "index"; 
    }
    
    @GetMapping("/quiz1")
    public String mostrarQuiz1() {
    return "quiz1";
    }

    @GetMapping("/quiz2")
    public String mostrarQuiz2() {
    return "quiz2";
    }

    @GetMapping("/quiz3")
    public String mostrarQuiz3() {
    return "quiz3";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
    return "login";
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
    return "dashboard";
    }

    /* CAMBIAR ACA PARA QUIZ SELECT
     * @GetMapping("/quiz-select")
    public String mostrarQuizSelect() {
    return "quiz-select";
}
     */
}