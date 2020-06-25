package com.skuniv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IntroController {

    //홈페이지 메인 - index 화면 이동
    @RequestMapping(value = "/")
    public String intro(Model model, HttpSession session) {
        if (model.getAttribute("SIGNUPSUCESS") == null) {
            model.addAttribute("SIGNUPSUCESS", false);
        }
        return "index";
    }

    //홈페이지 소개 - about 화면이동
    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }

}
