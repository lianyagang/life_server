package com.whoiszxl.zero.service;

import com.whoiszxl.zero.entity.Member;
import com.whoiszxl.zero.entity.params.AddMemberParam;
import com.whoiszxl.zero.entity.params.LoginParam;
import com.whoiszxl.zero.entity.vo.MemberDetailVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberService {

    /**
     * 会员信息查询
     *
     * @return
     */
    MemberDetailVO memberInfo();

    /**
     * 获取所有会员信息
     */

    List<MemberDetailVO> getAllMemberInfo();

    /**
     * 获取分页会员信息
     */
    Page<Member> getPageMember(int page, int size);


    /**
     * 新增会员 如果是管理员
     */
    boolean addChileMember(AddMemberParam loginParam);

    /**
     * 删除会员 只有管理员才有权限
     */
    boolean removeChildMember(Long memberId);
}
