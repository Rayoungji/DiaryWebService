package com.skuniv.diary.service;

import com.skuniv.diary.dao.DiaryDao;
import com.skuniv.diary.entity.Diary;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class GetDiaryByIdService {
    @Autowired
    private DiaryDao diaryDao;

    public Diary getDiaryById(int id){
        return diaryDao.selectById(id);
    }
}
