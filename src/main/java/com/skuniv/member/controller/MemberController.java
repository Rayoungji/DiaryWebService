package com.skuniv.member.controller;

import com.skuniv.diary.entity.Diary;
import com.skuniv.diary.service.GetDiaryListService;
import com.skuniv.diary.service.InsertDiaryService;
import com.skuniv.member.dto.SignInDto;
import com.skuniv.member.dto.SignUpDto;
import com.skuniv.member.dto.UpdateDto;
import com.skuniv.member.entity.Member;
import com.skuniv.member.service.DeleteService;
import com.skuniv.member.service.GetMemberService;
import com.skuniv.member.service.SignUpService;
import com.skuniv.member.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private GetMemberService getMemberService;
    @Autowired
    private UpdateService updateService;
    @Autowired
    private DeleteService deleteService;
    @Autowired
    private GetDiaryListService getDiaryListService;
    @Autowired
    private InsertDiaryService insertDiaryService;

    @GetMapping(value = "/mypage")
    private String myPage(Model model, HttpSession session) {
        System.out.println("is mypage running?");
        String email = (String) session.getAttribute("email");
        Member member = getMemberService.getMemberByEmail(email);
        List<Diary> diaryList = getDiaryListService.getDiaryListByEmail(email);
        model.addAttribute("member",member);
        if(diaryList != null){
        model.addAttribute("diaryList",diaryList);}
        if(diaryList == null){
            Date startDate = new Date();
            insertDiaryService.insertDiary(
                    Diary.builder()
                    .email(email)
                    .date(LocalDate.now())
                    .title("가입일")
                    .context("서비스 시작일")
                    .modify_at(startDate).build()
            );
            List<Diary> diaryList2 = getDiaryListService.getDiaryListByEmail(email);
            model.addAttribute("diaryList",diaryList2);
        }
        return "myPage";
    }

    @GetMapping(value = "/userinfo")
    private String userInfo(Model model, HttpSession session){
        String email = (String) session.getAttribute("email");
        Member member = getMemberService.getMemberByEmail(email);
        model.addAttribute("member",member);
        return "userInfo";
    }

    @GetMapping(value = "/userinfomodify")
    private String userModify(Model model, HttpSession session){
        String email = (String) session.getAttribute("email");
        Member member = getMemberService.getMemberByEmail(email);
        model.addAttribute("member",member);
        return "userInfoModify";
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
        session.setAttribute("email", mem.getEmail());
        if (model.getAttribute("SIGNUPSUCESS") == null) {
            model.addAttribute("SIGNUPSUCESS", false);
        }
        return "loginSuccess";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("email");
        model.addAttribute("SINGUPSUCESS",false);
        return "logoutSuccess";
    }

    @PostMapping(value = "/update.do")
    public String update(Model model, UpdateDto updateDto, HttpSession session){
        String email = (String)session.getAttribute("email");
        Member update = updateDto.toEntity(updateDto);
        updateService.updateMember(update,email);
        session.removeAttribute("email");
        model.addAttribute("SINGUPSUCESS",false);
        return "updateSuccess";
    }

    @PostMapping(value = "/deleteaccount")
    public String deleteAccount(){
        return "deleteAccount";
    }

    @PostMapping(value = "/pwcheck")
    public String pwCheck(HttpSession session, @RequestParam(value = "password", required = false)String password){
        String email = (String)session.getAttribute("email");
        Member member = getMemberService.getMemberByEmail(email);
        if(member.getPassword().equals(password)){
            session.removeAttribute("email");
            deleteService.deleteMember(member.getEmail());
            return "deleteAccountSuccess";
        }
        return "deleteAccountFail";
    }

}
