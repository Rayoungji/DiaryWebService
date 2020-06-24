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
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private GetMemberService getMemberService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        List<Member> memberList = getMemberService.getAllMember();
        model.addAttribute("members",memberList);
        return "list";
    }

    @GetMapping(value = "/signUp")
    public String signUp(){
        return "signUp";
    }

    @PostMapping("/signUp.do")
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
        signUpService.signUpMember(successMem);
        return "signIn";
    }

    @GetMapping("/signIn")
    public String signIn(HttpSession session, Model model) {
        session.invalidate();
        return "signIn";
    }

    @PostMapping("/signIn.do")
    public String signInComplete(SignInDto signInDto, HttpSession session, Model model) {
        Member mem = getMemberService.getMemberByEmail(signInDto.getEmail());
        if (mem == null) {
            return "signInFail";
        }
        if (!mem.getPassword().equals(signInDto.getPassword())) {
            return "signInpwFail";
        }
        session.setAttribute("name", mem.getName());
        session.setAttribute("email",mem.getEmail());
        model.addAttribute("LOGIN_OK",true);
        return "signInSuccess";
    }
}
