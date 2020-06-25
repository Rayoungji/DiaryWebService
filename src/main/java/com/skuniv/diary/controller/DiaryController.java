package com.skuniv.diary.controller;

import com.skuniv.diary.dao.DiaryDao;
import com.skuniv.diary.dto.DairyInsertDto;
import com.skuniv.diary.dto.DiaryDeleteDto;
import com.skuniv.diary.dto.DiaryModifyDto;
import com.skuniv.diary.entity.Diary;
import com.skuniv.diary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private InsertDiaryService insertDiaryService;
    @Autowired
    private ModifyDiaryService modifyDiaryService;
    @Autowired
    private GetDiaryByIdService getDiaryByIdService;
    @Autowired
    private DeleteDiarySerivce deleteDiarySerivce;

    //일기 작성 화면 - insertDiary로 화면 이동
    @GetMapping(value = "/insertdiary")
    public String insertDiaryPage(){
        return "insertDiary";
    }

    //insertDiary에서 폼 실행 부분
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

        Boolean check = insertDiaryService.insertDiary(
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

    //일기 상세보기 화면 - diaryDetail로 화면 이동
    @GetMapping(value = "/diarydetail")
    public String detailDiary (Model model, @RequestParam(value = "id", required = false)int id){
        Diary diary = getDiaryByIdService.getDiaryById(id);
        model.addAttribute("diary",diary);
        return "diaryDetail";
    }

    //일기 상세보기 화면에서 일기 수정 클릭 시 이동 - diaryModify로 화면 이동
    @GetMapping(value = "/diarymodify")
    private String diaryModify(Model model, @RequestParam(value = "id", required = false)int id){
        Diary diary = getDiaryByIdService.getDiaryById(id);
        model.addAttribute("diary",diary);
        return "diaryModify";
    }

    //diaryModify에서 폼 실행 부분
    @PostMapping(value = "/diarymodify.do")
    private String diaryModifyDo(HttpSession session, DiaryModifyDto diaryModifyDto){
        String email = (String)session.getAttribute("email");
        String str[]=diaryModifyDto.getDate().split("-");
        int Y = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int d = Integer.parseInt(str[2]);
        LocalDate localDate = LocalDate.of(Y,m,d);
        Date modifyDate = new Date();
        modifyDiaryService.modifyDairy(
                Diary.builder()
                .email(email)
                .date(localDate)
                .title(diaryModifyDto.getTitle())
                .context(diaryModifyDto.getContext())
                .modify_at(modifyDate).build()
        );
        return "modifyDiarySuccess";
    }

    @PostMapping(value = "/deletediary")
    public String deleteDiary(DiaryDeleteDto diaryDeleteDto){
        Diary deleteDiary = getDiaryByIdService.getDiaryById(Integer.parseInt(diaryDeleteDto.getId()));
        deleteDiarySerivce.deleteDiary(deleteDiary);
        return "deleteDiarySuccess";
    }
}
