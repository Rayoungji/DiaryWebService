package com.skuniv.config;

import com.skuniv.member.dao.MemberDao;
import com.skuniv.member.service.GetMemberService;
import com.skuniv.member.service.SignUpService;
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
}
