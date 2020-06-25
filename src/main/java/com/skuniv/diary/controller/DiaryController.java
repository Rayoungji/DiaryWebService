package com.skuniv.diary.controller;

import com.skuniv.diary.dao.DiaryDao;
import com.skuniv.diary.dto.DairyInsertDto;
import com.skuniv.diary.entity.Diary;
import com.skuniv.member.dao.MemberDao;
import com.skuniv.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;


@Controller
public class DiaryController {
    @Autowired
    private DiaryDao diaryDao;

    @GetMapping(value = "/insertdiary")
    public String insertDiaryPage(){
        return "insertDiary";
    }

    @PostMapping(value = "/insertdiary.do")
    public String insertDiary(HttpSession session, DairyInsertDto dairyInsertDto){
        String email = (String)session.getAttribute("email");
        String date[] = dairyInsertDto.getDate().split("-");
        System.out.println(date);
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        LocalDate localDate = LocalDate.of(year,month,day);
        Date modifyDate = new Date();

        Boolean check = diaryDao.saveDiary(
                Diary.builder()
                        .email(email)
                        .date(localDate)
                        .title(dairyInsertDto.getTitle())
                        .context(dairyInsertDto.getContext())
                        .modify_at(modifyDate).build()
        );

        if(check){
            return "insertDiarySuccess";
        }

        return "insertDiaryFail";
    }

    @GetMapping(value = "/diarydetail")
    public String detailDiary (Model model, @RequestParam(value = "id", required = false)int id){
        Diary diary = diaryDao.selectById(id);
        model.addAttribute("diary",diary);
        return "diaryDetail";
    }

    @GetMapping(value = "/diarymodify")
    private String diaryModify(Model model, @RequestParam(value = "id", required = false)int id){
        Diary diary = diaryDao.selectById(id);
        model.addAttribute("diary",diary);
        return "diaryModify";
    }

//    @PostMapping(value = "/modifydiary.do")
//    private String diaryModifyDo(){
//
//    }
}
