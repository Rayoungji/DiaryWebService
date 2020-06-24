package com.skuniv.member.controller;

import com.skuniv.member.dto.SignInDto;
import com.skuniv.member.dto.SignUpDto;
import com.skuniv.member.entity.Member;
import com.skuniv.member.service.GetMemberService;
import com.skuniv.member.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class MemberController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private GetMemberService getMemberService;

    @GetMapping(value = "/mypage")
    private String myPage() {
        return "myPage";
    }

    @GetMapping(value = "/signup")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/signup.do")
    public String signUpComplete(Model model, SignUpDto signUpDto) {
        signUpDto.setCreated_at(LocalDateTime.now());
        Member mem = getMemberService.getMemberByEmail(signUpDto.getEmail());
        if (mem != null) {
            return "alreadyExist";
        }
        if (signUpDto.getPassword().equals("") || signUpDto.getEmail().equals("")) {
            return "inputCheck";
        }
        Member successMem = signUpDto.toEntity(signUpDto);
        model.addAttribute("SIGNUPSUCESS", true);
        if (model.getAttribute("LOGINSUCESS") == null) {
            model.addAttribute("LOGINSUCESS", false);
        }
        signUpService.signUpMember(successMem);
        return "index";
    }

    @GetMapping("/signin")
    public String signIn(HttpSession session) {
        session.invalidate();
        return "signIn";
    }

    @PostMapping("/signin.do")
    public String signInComplete(SignInDto signInDto, HttpSession session, Model model) {
        Member mem = getMemberService.getMemberByEmail(signInDto.getEmail());
        if (mem == null) {
            return "signInFail";
        }
        if (!mem.getPassword().equals(signInDto.getPassword())) {
            return "signInpwFail";
        }
        session.setAttribute("name", mem.getName());
        session.setAttribute("email", mem.getEmail());
        if (model.getAttribute("SIGNUPSUCESS") == null) {
            model.addAttribute("SIGNUPSUCESS", false);
        }
        model.addAttribute("LOGINSUCESS", true);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("name");
        session.removeAttribute("email");
        model.addAttribute("SINGUPSUCESS",false);
        model.addAttribute("SIGNUPSUCESS",false);
        return "logoutSuccess";
    }
}
