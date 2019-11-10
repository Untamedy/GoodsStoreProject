package com.store.goodsstore.config;

import com.store.goodsstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *
 * @author YBolshakova
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
                
    }
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/start", "/login", "/forgotPass", "/error", "/signup").permitAll()
                .antMatchers("/admin/**").hasAnyRole("admin")
                .antMatchers("/goods/**", "/reports/**").hasAnyRole("user")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginPage").loginProcessingUrl("/j_spring_security_check").failureUrl("/error")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll()
                .and()
                .logout()
                .permitAll().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true)
                .and()                
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
       

    }

  
}