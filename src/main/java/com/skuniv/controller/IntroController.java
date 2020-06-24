package com.skuniv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroController {
    @RequestMapping(value = "/")
    public String intro(Model model) {
        if (model.getAttribute("SIGNUPSUCESS") == null) {
            model.addAttribute("SIGNUPSUCESS", false);
        }
        if (model.getAttribute("LOGINSUCESS") == null) {
            model.addAttribute("LOGINSUCESS", false);
        }
        return "index";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }
}
