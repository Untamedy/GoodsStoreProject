package com.store.goodsstore.config;

import com.store.goodsstore.services.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    private UserSecurityService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
        
    }
    
  /*  @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.
              //  .userDetailsService(userService)
              //  .passwordEncoder(passwordEncoder);                
    }*/
  
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/registration", "/login","/forgotPass", "/error", "/signup","/restorePass","/sendEmail").permitAll()
                .antMatchers("/","/store/**","/group/**","/allstore",  "/goods/**","/inputpage/**","/incomePage/**","/show","/admin/**","/logout").hasAnyRole("ADMIN")
                .antMatchers("/","/store/**","/group/**","/allstore",  "/goods/**","/inputpage/**","/incomePage/**","/show","/logout","/logout").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
                .loginProcessingUrl("/dologin")
                .successForwardUrl("/store").failureUrl("/error")                
                .permitAll()
                .and()
                .logout()
                .permitAll().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true)
                .and()                
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);      

    }

  
}
