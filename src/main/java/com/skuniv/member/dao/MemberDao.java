package com.skuniv.member.dao;

import com.skuniv.member.entity.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberDao {
    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertMember(Member member) {
        System.out.println("insertMemberDao is running");
        jdbcTemplate.update("insert into member (name, phone, address, email, password, created_at) values (?, ?, ?, ?, ?, ?)",
                member.getName(), member.getPhone(), member.getAddress(), member.getEmail(), member.getPassword(), member.getCreated_at());
    }

    public Member selectMemberByEmail(String email) {
        System.out.println("selectMemberByEmailDao is running");
        List<Member> results = jdbcTemplate.query("select * from member where email =?",
                new RowMapper<Member>() {
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Member member = new Member(rs.getString("name"),
                                rs.getString("phone"),
                                rs.getString("address"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getTimestamp("created_at").toLocalDateTime());
                        member.setId(rs.getLong("id"));
                        return member;
                    }
                }, email);
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Member> selectAllMember() {
        System.out.println("selectAllMemberDao is running");
        List<Member> results = jdbcTemplate.query("select * from member",
                new RowMapper<Member>() {
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Member member = new Member(
                                rs.getString("name"),
                                rs.getString("phone"),
                                rs.getString("address"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getTimestamp("created_at").toLocalDateTime());
                        member.setId(rs.getLong("id"));
                        return member;
                    }
                });
        return results.isEmpty() ? null : results;
    }
}

