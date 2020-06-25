package com.skuniv.diary.controller;

import com.skuniv.diary.dao.DiaryDao;
import com.skuniv.diary.dto.*;
import com.skuniv.diary.entity.Diary;
import com.skuniv.diary.service.*;
import com.skuniv.member.entity.Member;
import com.skuniv.member.service.GetMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Controller
public class DiaryController {
    @Autowired
    private GetDiaryListService getDiaryListService;
    @Autowired
    private InsertDiaryService insertDiaryService;
    @Autowired
    private ModifyDiaryService modifyDiaryService;
    @Autowired
    private GetDiaryByIdService getDiaryByIdService;
    @Autowired
    private DeleteDiarySerivce deleteDiarySerivce;
    @Autowired
    private SearchDiaryService searchDiaryService;

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

    @GetMapping(value = "/diarylist")
    public String diaryList(Model model, HttpSession session){
        System.out.println("diaryList Controller running");
        String email = (String) session.getAttribute("email");
        List<Diary> diaryList = getDiaryListService.getDiaryListByEmail(email);
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
        return "diaryList";
    }

    @GetMapping(value = "/durationsearch")
    public String durationForm(){
        return "durationForm";
    }

    @PostMapping(value = "/durationsearch.do")
    public String durationSearch(DurationDto durationDto, HttpSession session,Model model){
        String startStr[]=durationDto.getStartDate().split("-");
        int Y = Integer.parseInt(startStr[0]);
        int m = Integer.parseInt(startStr[1]);
        int d = Integer.parseInt(startStr[2]);
        LocalDate startLocatDate = LocalDate.of(Y,m,d);

        String endStar[]=durationDto.getEndDate().split("-");
        int Y2 = Integer.parseInt(startStr[0]);
        int m2 = Integer.parseInt(startStr[1]);
        int d2 = Integer.parseInt(startStr[2]);
        LocalDate endLocalDate = LocalDate.of(Y2,m2,d2);
        String email = (String)session.getAttribute("email");
        DurationReturnDto durationReturnDto=DurationReturnDto.builder()
                .startDate(startLocatDate)
                .endDate(endLocalDate).build();
        List<Diary> diaryLists = searchDiaryService.durationSearchDiary(durationReturnDto,email);
        if(diaryLists == null){
            System.out.println("is nulllllllllll");
        }
        model.addAttribute("diaryList",diaryLists);
        return "diaryList";
    }

}
