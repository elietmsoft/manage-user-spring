package com.fumutik.manageclient.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("elie")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("elie")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("elie")).roles("USER","ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/",true);
        //httpSecurity.rememberMe();
       httpSecurity
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/webjars/**","/h2-console/**").permitAll()
                        .antMatchers("/login").permitAll()
                        //.antMatchers("/user/**").hasRole("USER")
                        //.antMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        //httpSecurity.authorizeRequests().requestMatchers("/user/**").hasRole("USER");
        // httpSecurity.authorizeRequests().anyRequest().authenticated();
        return httpSecurity.build();
       // return httpSecurity.getOrBuild();
    }
}

/**
 * Java 17
 *  httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
 * */