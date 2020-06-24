package com.skuniv.member.service;

import com.skuniv.member.dao.MemberDao;
import com.skuniv.member.entity.Member;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@NoArgsConstructor
public class GetMemberService {
    @Autowired
    public MemberDao memberDao;

    public Member getMemberByEmail(String email){
        return memberDao.selectMemberByEmail(email);
    }

    public List<Member> getAllMember(){
        return memberDao.selectAllMember();
    }
}
