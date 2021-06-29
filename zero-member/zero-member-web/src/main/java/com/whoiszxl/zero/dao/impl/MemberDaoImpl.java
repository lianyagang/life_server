package com.whoiszxl.zero.dao.impl;

import com.whoiszxl.zero.dao.MemberDao;
import com.whoiszxl.zero.entity.Member;
import com.whoiszxl.zero.entity.params.AddMemberParam;
import com.whoiszxl.zero.repository.MemberRepository;
import com.whoiszxl.zero.utils.PasswordEncodeUtil;
import io.micrometer.core.instrument.util.TimeUtils;
import org.bouncycastle.asn1.dvcs.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).get();
    }

    @Override
    public Member register(String userName, String password) {
        Member member = new Member();
        member.setId(3L);
        member.setUsername(userName);
        member.setPassword(password);
        member.setStatus(0);
        member.setPayPassword(password);
        member.setCreatedAt(new Date());
        member.setUpdatedAt(new Date());
        return memberRepository.save(member);
    }


    @Override
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public Page<Member> getPageMember(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Member addChileMember(AddMemberParam param) {
        Member member = new Member();
        String password = PasswordEncodeUtil.bcryptEncode(param.getPassword());

        member.setUsername(param.getUsername());
        member.setPassword(password);
        member.setStatus(0);
        member.setPayPassword(password);
        member.setEmail(param.getEmail());
        member.setRealName(param.getName());
        member.setPhone(param.getPhone());
        member.setCreatedAt(new Date());
        member.setUpdatedAt(new Date());
        return memberRepository.save(member);
    }

    @Override
    public boolean removeChildMember(Long memberId) {
        try {
            memberRepository.deleteById(memberId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
