package com.skuniv.diary.dao;

import com.skuniv.diary.dto.DurationDto;
import com.skuniv.diary.dto.DurationReturnDto;
import com.skuniv.diary.entity.Diary;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DiaryDao {
    private JdbcTemplate jdbcTemplate;

    public DiaryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Boolean saveDiary(Diary diary) {

        List<Diary> results = jdbcTemplate.query("select * from diary where date =? and email=?",
                new RowMapper<Diary>() {
                    @Override
                    public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Diary getDiary = new Diary(rs.getString("email"),
                                rs.getDate("date").toLocalDate(),
                                rs.getString("title"),
                                rs.getString("context"),
                                rs.getDate("modify_at"));
                        getDiary.setId(rs.getInt("id"));
                        return getDiary;
                    }
                }, diary.getDate(), diary.getEmail());

        if (results.isEmpty()) {
            jdbcTemplate.update("insert into diary (email, date, title, context, modify_at) values (?, ?, ?, ?, ?)",
                    diary.getEmail(), diary.getDate(), diary.getTitle(), diary.getContext(), diary.getModify_at());
            return true;
        }

        if (!(results.isEmpty())) {
            return false;
        }

        return null;
    }

    public void updateDiary(Diary update) {
        System.out.println("updateDairyDao is running");
        jdbcTemplate.update("update diary set title=?, context=?, modify_at=? where email=? and date=?",
                update.getTitle(), update.getContext(), update.getModify_at(), update.getEmail(), update.getDate());
    }

    public void deleteDiary(Diary deleteDiary) {
        System.out.println("deleteDiaryDao is running");
        jdbcTemplate.update("delete from diary where email=? and date=?",
                deleteDiary.getEmail(), deleteDiary.getDate());
    }

    public Diary selectById(int id){
        List<Diary> results = jdbcTemplate.query("select * from diary where id =?",
                new RowMapper<Diary>() {
                    @Override
                    public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Diary member = new Diary(rs.getString("email"),
                                rs.getDate("date").toLocalDate(),
                                rs.getString("title"),
                                rs.getString("context"),
                                rs.getDate("modify_at"));
                        member.setId(rs.getInt("id"));
                        return member;
                    }
                }, id);
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Diary> selectAllByEmail(String email){
        List<Diary> results = jdbcTemplate.query("select * from diary where email =?  ORDER BY date ASC",
                new RowMapper<Diary>() {
                    @Override
                    public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Diary getDiary = new Diary(rs.getString("email"),
                                rs.getDate("date").toLocalDate(),
                                rs.getString("title"),
                                rs.getString("context"),
                                rs.getDate("modify_at"));
                        getDiary.setId(rs.getInt("id"));
                        return getDiary;
                    }
                }, email);

        return results.isEmpty()? null:results;
    }

    public List<Diary> selectAllByDateDuration(DurationReturnDto diarySearchDate, String email) {
        System.out.println("selectAllByDateDurationDao is running");
        LocalDate startDate=diarySearchDate.getStartDate();
        LocalDate endDate=diarySearchDate.getEndDate();
        List<Diary> results = jdbcTemplate.query("select * from diary WHERE date(date) >=?  and date(date) <=? and email=? ORDER BY date ASC",
                new RowMapper<Diary>() {
                    @Override
                    public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Diary getDiary = new Diary(rs.getString("email"),
                                rs.getDate("date").toLocalDate(),
                                rs.getString("title"),
                                rs.getString("context"),
                                rs.getDate("modify_at"));
                        getDiary.setId(rs.getInt("id"));
                        return getDiary;
                    }
                }, startDate, endDate, email);

        return results.isEmpty()? null:results;

    }

    public List<Diary> selectAllByKeywordAndEmail(String keyword, String email)  {
        List<Diary> results = jdbcTemplate.query("select * from diary WHERE email=? and context like ? ORDER BY date ASC",
                new RowMapper<Diary>() {
                    @Override
                    public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Diary getDiary = new Diary(rs.getString("email"),
                                rs.getDate("date").toLocalDate(),
                                rs.getString("title"),
                                rs.getString("context"),
                                rs.getDate("modify_at"));
                        getDiary.setId(rs.getInt("id"));
                        return getDiary;
                    }
                }, email, "%"+keyword+"%");

        return results.isEmpty()? null:results;
    }

    public List<Diary> selectAllByTitleAndEmail(String title, String email){
        List<Diary> results = jdbcTemplate.query("select * from diary WHERE email=? and title like ? ORDER BY date ASC",
                new RowMapper<Diary>() {
                    @Override
                    public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Diary getDiary = new Diary(rs.getString("email"),
                                rs.getDate("date").toLocalDate(),
                                rs.getString("title"),
                                rs.getString("context"),
                                rs.getDate("modify_at"));
                        getDiary.setId(rs.getInt("id"));
                        return getDiary;
                    }
                }, email,  "%" + title + "%");

        return results.isEmpty()? null:results;
    }
}
