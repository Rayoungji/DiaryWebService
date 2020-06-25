package com.skuniv.diary.service;

import com.skuniv.diary.dao.DiaryDao;
import com.skuniv.diary.entity.Diary;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class InsertDiaryService {
    @Autowired
    private DiaryDao diaryDao;

    public Boolean insertDiary(Diary diary){
        return diaryDao.saveDiary(diary);
    }
}
