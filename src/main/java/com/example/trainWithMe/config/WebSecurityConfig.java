package com.example.trainWithMe.config;

import com.example.trainWithMe.service.UserDetailsServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImplementation userDetailsServiceImplementation;

    public WebSecurityConfig(UserDetailsServiceImplementation userDetailsServiceImplementation) {
        this.userDetailsServiceImplementation = userDetailsServiceImplementation;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImplementation);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();
        http.authorizeRequests()
                .antMatchers("/hello").authenticated()
                .antMatchers("/myPlan").hasRole("USER")
                .antMatchers("/progress").hasRole("USER")
                .antMatchers("/excercises").hasRole("USER")
                .antMatchers("/about").hasRole("USER")
                .antMatchers("/addUnit").hasRole("ADMIN")
                .antMatchers("/myInfo").hasRole("USER")
                .antMatchers("/static/favicon.ico").permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/hello");
    }
}
