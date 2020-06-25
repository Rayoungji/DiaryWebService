package com.skuniv.diary.service;

import com.skuniv.diary.dao.DiaryDao;
import com.skuniv.diary.entity.Diary;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor
public class GetDiaryListService {
    @Autowired
    private DiaryDao diaryDao;

    public List<Diary> getDiaryListByEmail(String email) {
        return diaryDao.selectAllByEmail(email);
    }
}
