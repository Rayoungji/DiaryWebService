package com.skuniv.diary.service;

import com.skuniv.diary.dao.DiaryDao;
import com.skuniv.diary.dto.DurationReturnDto;
import com.skuniv.diary.entity.Diary;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@NoArgsConstructor
public class SearchDiaryService {
    @Autowired
    private DiaryDao diaryDao;

    public List<Diary> durationSearchDiary(DurationReturnDto durationDto, String email){
        return diaryDao.selectAllByDateDuration(durationDto,email);
    }

    public List<Diary> keywordSearchDiary(String keyword, String email){
        return diaryDao.selectAllByKeywordAndEmail(keyword,email);
    }

    public List<Diary> titleSearchDiary(String title, String email){
        return diaryDao.selectAllByTitleAndEmail(title,email);
    }
}
