package com.whoiszxl.zero.controller;

import com.whoiszxl.zero.bean.Result;
import com.whoiszxl.zero.entity.params.LoginParam;
import com.whoiszxl.zero.entity.vo.LoginMemberVO;
import com.whoiszxl.zero.service.MemberLoginService;
import com.whoiszxl.zero.utils.PasswordEncodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "会员登录控制器")
@RestController
public class LoginController {

    @Autowired
    private MemberLoginService memberLoginService;

    @PostMapping("/login")
    @ApiOperation(value = "后台管理员登录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "loginParam", value = " 登录参数")
            }
    )
    public Result<LoginMemberVO> login(@RequestBody LoginParam loginParam) {
        return Result.buildSuccess(memberLoginService.login(loginParam));
    }

    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody LoginParam registerParam) {
        return Result.buildSuccess(memberLoginService.register(registerParam));
    }

}
