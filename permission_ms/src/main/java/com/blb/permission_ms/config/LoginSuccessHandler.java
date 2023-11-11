package com.blb.permission_ms.config;

import com.blb.permission_ms.entity.ResponseResult;
import com.blb.permission_ms.utils.JwtUtils;
import com.blb.permission_ms.utils.RsaUtils;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //获得登录的用户信息
        User user = (User) authentication.getPrincipal();
        //将用户信息转换为JWT
        String token = JwtUtils.generateToken(user.getUsername(), RsaUtils.privateKey, JwtUtils.EXPIRE_MINUTES);
        System.out.println("生成JWT:" + token);
        //将JWT包装到ResponseResult中转换为json格式=发送json
        ResponseResult.write(httpServletResponse,ResponseResult.ok(token));
    }
}
