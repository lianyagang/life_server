package com.whoiszxl.zero.service.impl;

import com.whoiszxl.zero.dao.MemberDao;
import com.whoiszxl.zero.dao.MemberInfoDao;
import com.whoiszxl.zero.entity.Member;
import com.whoiszxl.zero.entity.MemberInfo;
import com.whoiszxl.zero.entity.params.AddMemberParam;
import com.whoiszxl.zero.entity.params.LoginParam;
import com.whoiszxl.zero.entity.vo.MemberDetailVO;
import com.whoiszxl.zero.entity.vo.MemberInfoVO;
import com.whoiszxl.zero.entity.vo.MemberVO;
import com.whoiszxl.zero.service.MemberService;
import com.whoiszxl.zero.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberInfoDao memberInfoDao;

    @Override
    public MemberDetailVO memberInfo() {
        //从jwt中获取用户ID
        Long memberId = Long.parseLong(JwtUtils.getUsername());
        Member member = memberDao.findById(memberId);
        MemberInfo memberInfo = memberInfoDao.findByMemberId(memberId);
        MemberVO memberVO = member.clone(MemberVO.class);
        MemberInfoVO memberInfoVO = memberInfo.clone(MemberInfoVO.class);

        MemberDetailVO memberDetailVO = new MemberDetailVO();
        memberDetailVO.setMemberVO(memberVO);
        memberDetailVO.setMemberInfoVO(memberInfoVO);

        return memberDetailVO;
    }

    @Override
    public List<MemberDetailVO> getAllMemberInfo() {
        List<Member> allMember = memberDao.getAllMember();
        List<MemberDetailVO> memberDetailVOList = new ArrayList<>();
        for (Member member : allMember) {
            MemberVO memberVO = member.clone(MemberVO.class);
            MemberDetailVO memberDetailVO = new MemberDetailVO();
            memberDetailVO.setMemberVO(memberVO);
            Long memberId = member.getId();
            MemberInfo memberInfo = memberInfoDao.findByMemberId(memberId);
            if (memberInfo != null) {
                MemberInfoVO memberInfoVO = memberInfo.clone(MemberInfoVO.class);
                memberDetailVO.setMemberInfoVO(memberInfoVO);
            }
            memberDetailVOList.add(memberDetailVO);
        }

        return memberDetailVOList;
    }

    @Override
    public Page<Member> getPageMember(int page, int size) {
        return memberDao.getPageMember(page,size);
    }

    @Override
    public boolean addChileMember(AddMemberParam loginParam) {
        //从jwt中获取用户ID
        Long memberId = Long.parseLong(JwtUtils.getUsername());
        Member member = memberDao.findById(memberId);
        if (member != null && 1 == member.getStatus()) {
            Member chileMember = memberDao.addChileMember(loginParam);
            return chileMember != null;
        }
        return false;
    }

    @Override
    public boolean removeChildMember(Long memberId) {
        //从jwt中获取用户ID
        Long id = Long.parseLong(JwtUtils.getUsername());
        Member member = memberDao.findById(id);
        if (member != null && 1 == member.getStatus()) {
            return memberDao.removeChildMember(memberId);
        }
        return false;
    }
}
