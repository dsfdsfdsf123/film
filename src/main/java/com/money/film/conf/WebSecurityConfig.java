package com.money.film.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Configuration注解可以用java代码的形式实现spring中xml配置文件配置的效果。
 * spring security配置
 * @author liugang
 * @create 2018/10/23 23:13
 **/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置用户认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                passwordEncoder(new BCryptPasswordEncoder()).
                withUser("root").
                password(new BCryptPasswordEncoder().encode("123456")).
                roles("ADMIN");
    }

    /**
     * 请求授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().headers().disable().
                authorizeRequests()
                .antMatchers("/","/static/**","/film/**","/webSite/**","/webSiteInfo/**","/aboutMe","/getValue","/testRedis/**","/admin/film/uploadImageToYun").permitAll()//不需要身份认证的地址
                .anyRequest().authenticated()//其他所有访问路径需要身份认证
                .and()
                .formLogin()
                .loginPage("/login")//指定登录请求地址
                .defaultSuccessUrl("/admin")//登录成功后默认跳转的页面
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();//这是放行
        //那些路径不需要权限认证
    }
}
