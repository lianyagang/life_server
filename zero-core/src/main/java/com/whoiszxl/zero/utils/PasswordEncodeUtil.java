package com.whoiszxl.zero.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author goldhuang
 * @Description: oauth 密码加密。 可以到 PasswordEncodeUtilTest 进行测试
 * @date 2020-09-01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordEncodeUtil {

    private static final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    public static String bcryptEncode(String password) {
        return bcryptEncoder.encode(password);
    }

    public static String genOauthEncodePwd(String password) {
        return "{bcrypt}" + bcryptEncode(password);
    }

    public static boolean match(String rawPwd,String salt){
        return bcryptEncoder.matches(rawPwd,salt);
    }
}
