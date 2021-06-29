package com.whoiszxl.zero.dao;

import com.whoiszxl.zero.entity.Member;
import com.whoiszxl.zero.entity.params.AddMemberParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberDao {

    /**
     * 通过会员Id查找会员详情
     * @param id 会员ID
     * @return
     */
    Member findById(Long id);

    /**
     * 用户注册
     */
    Member register(String userName,String password);


    /**
     * 获取所有会员信息
     */
    List<Member> getAllMember();

    /**
     * 分页获取会员信息
     */
    Page<Member> getPageMember(int page, int size);

    /**
     * 新增会员
     */
    Member addChileMember(AddMemberParam param);

    boolean removeChildMember(Long memberId);
}
