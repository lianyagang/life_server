package com.whoiszxl.zero.controller;

import com.whoiszxl.zero.bean.Result;
import com.whoiszxl.zero.entity.Member;
import com.whoiszxl.zero.entity.params.AddMemberParam;
import com.whoiszxl.zero.entity.params.LoginParam;
import com.whoiszxl.zero.entity.vo.MemberDetailVO;
import com.whoiszxl.zero.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "会员控制器")
@RestController
public class MemberController {


    @Autowired
    private MemberService memberService;

    @PostMapping("/member/info")
    public Result<MemberDetailVO> memberInfo() {
        MemberDetailVO memberInfoVO = memberService.memberInfo();
        return Result.buildSuccess(memberInfoVO);
    }
    @GetMapping("/getAllMember")
    public Result<List<MemberDetailVO>> getAllMember() {
        List<MemberDetailVO> memberInfoVO = memberService.getAllMemberInfo();
        return Result.buildSuccess(memberInfoVO);
    }

    @GetMapping("/getPageMember")
    public Result<List<Member>> getPageMember(@RequestParam int page,@RequestParam int size) {
        Page<Member> pageMember = memberService.getPageMember(page, size);
        List<Member> content = pageMember.getContent();
        return Result.buildSuccess(content);
    }

    @PostMapping("/addChildMember")
    public Result<Boolean> register(@RequestBody AddMemberParam registerParam) {
        return Result.buildSuccess(memberService.addChileMember(registerParam));
    }

    @DeleteMapping("/removeMember")
    public Result<Boolean> remove(@RequestParam Long memberId) {
        return Result.buildSuccess(memberService.removeChildMember(memberId));
    }
}
