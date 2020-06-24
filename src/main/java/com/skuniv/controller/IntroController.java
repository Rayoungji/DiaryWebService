package com.skuniv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroController {
    @GetMapping(value = "/")
    public String intro(Model model){
        model.addAttribute("SIGNUPSUCESS", false);
        return "index";
    }

    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }
}
