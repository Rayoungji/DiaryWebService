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

    //myPages 이동 - attribute: 회원의 일기목록 & 회원정보
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

    //회원정보 화면 - userInfo로 화면 이동
    @GetMapping(value = "/userinfo")
    private String userInfo(Model model, HttpSession session){
        String email = (String) session.getAttribute("email");
        Member member = getMemberService.getMemberByEmail(email);
        model.addAttribute("member",member);
        return "userInfo";
    }

    //회원정보화면에서 수정버튼 클릭시 수정화면 - userInfoModify로 화면 이동
    @GetMapping(value = "/userinfomodify")
    private String userModify(Model model, HttpSession session){
        String email = (String) session.getAttribute("email");
        Member member = getMemberService.getMemberByEmail(email);
        model.addAttribute("member",member);
        return "userInfoModify";
    }

    //userInfoModify 화면에서 폼 실행 부분
    @PostMapping(value = "/update.do")
    public String update(Model model, UpdateDto updateDto, HttpSession session){
        String email = (String)session.getAttribute("email");
        Member update = updateDto.toEntity(updateDto);
        updateService.updateMember(update,email);
        session.removeAttribute("email");
        model.addAttribute("SINGUPSUCESS",false);
        return "updateSuccess";
    }

    //회원가입 화면 - signUp으로 화면 이동
    @GetMapping(value = "/signup")
    public String signUp() {
        return "signUp";
    }

    //signUp 화면에서 폼 실행 부분
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

    //로그인 화면 - signIn으로 화면 이동
    @GetMapping("/signin")
    public String signIn(HttpSession session) {
        session.invalidate();
        return "signIn";
    }

    //SignIn 화면에서 폼 실행 부분
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

    //로그아웃 버튼 실행 부분
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("email");
        model.addAttribute("SINGUPSUCESS",false);
        return "logoutSuccess";
    }

    //회원탈퇴 버튼 실행 부분
    @PostMapping(value = "/deleteaccount")
    public String deleteAccount(){
        return "deleteAccount";
    }

    //회원탈퇴 시 비밀번호 확인
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
