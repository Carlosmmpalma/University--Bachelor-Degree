package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.config;

import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	private UserAuthService userAuthService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService( userAuthService).passwordEncoder(passwordEncoder());
	}
        
	@Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                .antMatchers("/login*").permitAll() // Allow access to login page without requiring authentication
                .antMatchers("/gestao_anuncios*").hasRole("ADMIN") //Only allows admin to acess gestao_anuncios
                .antMatchers("/user_interface*").authenticated() // Allow authenticated users to access "user_interface" page
                .antMatchers("/regist_anun*").authenticated() // Allow authenticated users to access "regist_anun" page
                .antMatchers("/criar_anuncio*").authenticated() // Allow authenticated users to access "criar_anuncio" page
                .antMatchers("/anuncios_user*").authenticated() // Allow authenticated users to access "anuncios_user" page
                .antMatchers("/anuncios_sub_user*").authenticated() // Allow authenticated users to access "anuncios_sub_user" page
                 .antMatchers("/own_msgs").permitAll()
                .antMatchers("/*").permitAll()
                    
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/user_interface")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password");
        }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}