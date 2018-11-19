package com.builder.common.core.security;


import com.builder.common.base.constant.GlobalConstant;
import com.builder.common.base.enums.MonErrorCodeEnum;
import com.builder.common.base.exception.BusinessException;
import com.builder.common.base.utils.ThreadLocalMap;
import com.builder.common.core.dto.LoginAuthDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * RequestAuthUtils
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-30 16:57:53
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestAuthUtils {

    public static String[] extractAndDecodeHeader(String header) throws IOException {

        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, StandardCharsets.UTF_8);

        int delim = token.indexOf(GlobalConstant.Symbol.MH);

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }


    /**
     * 获取 login user.
     *
     * @return the login user
     */
    public static LoginAuthDto getLoginUser() {
        LoginAuthDto loginAuthDto = (LoginAuthDto) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        if (null == loginAuthDto) {
            throw new BusinessException(MonErrorCodeEnum.PCENTER100411);
        }
        return loginAuthDto;

    }

}
