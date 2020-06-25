package com.skuniv.config;

import com.skuniv.diary.dao.DiaryDao;
import com.skuniv.diary.service.*;
import com.skuniv.member.dao.MemberDao;
import com.skuniv.member.service.DeleteService;
import com.skuniv.member.service.GetMemberService;
import com.skuniv.member.service.SignUpService;
import com.skuniv.member.service.UpdateService;
import lombok.Builder;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/finalDiary?characterEncoding=utf8&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("youngji503");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(60000 * 3);
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
        return ds;
    }

    @Bean
    public MemberDao memberDao(){return new MemberDao(dataSource());}

    @Bean
    public SignUpService signUpService(){return new SignUpService();}

    @Bean
    public GetMemberService getMemberService(){return new GetMemberService();}

    @Bean
    public UpdateService updateService() {return new UpdateService();}

    @Bean
    public DeleteService deleteService() {return new DeleteService();}

    @Bean
    public DiaryDao diaryDao(){return new DiaryDao(dataSource());}

    @Bean
    public InsertDiaryService insertDiaryService(){return new InsertDiaryService();}

    @Bean
    public GetDiaryListService getDiaryListService() {return new GetDiaryListService();}

    @Bean
    public ModifyDiaryService modifyDiaryService() {return new ModifyDiaryService();}

    @Bean
    public GetDiaryByIdService getDiaryByIdService() {return new GetDiaryByIdService();}

    @Bean
    public DeleteDiarySerivce deleteDiarySerivce() {return new DeleteDiarySerivce();}

    @Bean
    public SearchDiaryService searchDiaryService() {return new SearchDiaryService();}
}
