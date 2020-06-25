package com.skuniv.diary.service;

import com.skuniv.diary.dao.DiaryDao;
import com.skuniv.diary.entity.Diary;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class ModifyDiaryService {
    @Autowired
    private DiaryDao diaryDao;

    public void modifyDairy(Diary diary){
        diaryDao.updateDiary(diary);
    }
}
