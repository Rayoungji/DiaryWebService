package com.skuniv.member.service;

import com.skuniv.member.dao.MemberDao;
import com.skuniv.member.entity.Member;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class UpdateService {
    @Autowired
    private MemberDao memberDao;

    public void updateMember(Member member, String email){
        memberDao.updateMember(member,email);
    }
}
