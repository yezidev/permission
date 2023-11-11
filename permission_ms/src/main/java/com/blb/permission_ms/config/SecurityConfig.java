package com.blb.permission_ms.config;

import com.blb.permission_ms.entity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Security配置类
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;

    /**
     * 提供密码加密编码器给IOC容器
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 验证配置
     * Authentication 验证，Authorization 授权
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //数据库登录验证，权限，角色
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 配置登录处理
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //请求授权
        http.authorizeRequests()
                .antMatchers("/login").permitAll() //配置直接放行的请求
                .anyRequest().authenticated() //其它请求需要登录验证
                .and()
                .formLogin() //登录相关配置
                .loginProcessingUrl("/login") //登录处理的url
                .successHandler(new LoginSuccessHandler()) //登录成功后的处理
                .failureHandler((httpServletRequest, httpServletResponse, e) -> { //登录失败处理器
                    ResponseResult.write(httpServletResponse, ResponseResult.error(401L, "登录失败，账号或密码错误"));
                })
                .and()
                .exceptionHandling() //处理未登录的请求
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> { //登录失败处理器
                    ResponseResult.write(httpServletResponse, ResponseResult.error(403L, "没有权限，请登录"));
                })
                .and()
                .logout() //注销配置
                .logoutUrl("/logout")
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, e) -> { //注销成功处理器
                    ResponseResult.write(httpServletResponse, ResponseResult.ok("注销成功"));
                })
                .clearAuthentication(true) //清除验证信息
                .and()
                .csrf().disable() //停止csrf
                .sessionManagement() //session管理
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //无状态，不使用session
                .and()
                .cors()
                .configurationSource(corsConfigurationSource())//跨域配置
                .and()
                //new出来的对象无法注入，没有交给IOC容器，创建一个applicationContext工具类实现注入
                .addFilter(new TokenAuthentcationFilter(authenticationManager()));//添加token请求验证的过滤器
    }

    /**
     * 跨域配置对象
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //配置允许访问的服务器域名
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
