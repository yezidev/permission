package com.blb.permission_ms.config;

import com.blb.permission_ms.service.UserService;
import com.blb.permission_ms.utils.ApplicationContextUtils;
import com.blb.permission_ms.utils.JwtUtils;
import com.blb.permission_ms.utils.RsaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Token请求验证过滤器
 */
@Slf4j
public class TokenAuthentcationFilter extends BasicAuthenticationFilter {

    public UserService userService = ApplicationContextUtils.getBean(UserService.class);

    public static final String HEADER_NAME = "Authorization";

    public TokenAuthentcationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 对请求进行过滤
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获得请求头
        String token = request.getHeader(HEADER_NAME);
        if (token == null) {
            log.info("没有token，被拦截");
            //放行,不给通行证，请求会被拦截
            chain.doFilter(request, response);
            return;
        }
        try {
            //解析token
            String username = JwtUtils.getUsernameFromToken(token, RsaUtils.publicKey);
            //查询权限
            List<GrantedAuthority> authorities = userService.getAuthoritiesByUserName(username);
            //创建通行证
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username,"",authorities);
            //交给security
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
            log.error("解析token失败",e);
        }
        chain.doFilter(request,response);
    }
}
