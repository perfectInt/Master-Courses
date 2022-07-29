package io.sultanov.mastercourses.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/signup/**").permitAll()
                .antMatchers("/actuator/shutdown").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/admin/user/delete/**").hasRole("ADMIN")
                .antMatchers("/api/lesson/edit/**").hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers("/api/lesson/create/**").hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers("/api/admin/changeaccess/**").hasAnyRole("ADMIN", "MODERATOR")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable().httpBasic();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}