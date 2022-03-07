package com.example.config;

// 용어
// Authentication : 로그인
// Authorization : 권한에 관한 설정

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/findId","/findPw","/join", "/showFindId","/showFindPw" ,"/showLoginSchedule", "/showJoinSchedule","/images/**", "/script/**", "/js/**").permitAll() // /와 정적 데이터는 모두 접근 가능
                .anyRequest().authenticated() // 위의 설정 외에는 모두 로그인을 해야 함
                .and()
            .formLogin()
                .loginPage("/").loginProcessingUrl("/login").permitAll()
                .usernameParameter("userId")
                .passwordParameter("userPw")
				.defaultSuccessUrl("/ScheduleManagement/")
                .and()
            .logout()
                .logoutSuccessUrl("/").permitAll()
                .and()
                .cors().and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select userId username, userPw password, enabled " // 순서 주의(로그인할 때 이 쿼리를 사용하게 됨 : 마이바티스에서 쿼리 필요없음)
                        + "from userlist "
                        + "where userId = ?") // 인증
                .authoritiesByUsernameQuery("select userId username, rolename role_name "
                        + "from userlist where userid = ? "); // 권한
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // 암호화
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
