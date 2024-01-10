package com.example.bookify.security;

import com.example.bookify.security.service.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
    @Autowired
    public UserDetailsServiceImplementation userDetailsServiceImplementation;
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager()
    {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password(passwordEncoder().encode("1234"))
                        .roles("USER")
                        .build(),
                User.withUsername("admin")
                        .password(passwordEncoder().encode("1234"))
                        .roles("ADMIN","USER")
                        .build()
        ); // ona creer des utilisateurs au niveau de la mémoire
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.formLogin(form->form.permitAll());
        // httpSecurity.formLogin(form->form.loginPage("/login"));
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.authorizeHttpRequests(authorize->authorize.requestMatchers("/admin/**").hasAuthority("ADMIN"));
        httpSecurity.authorizeHttpRequests(authorize->authorize.requestMatchers("/api/**").hasAuthority("USER"));
        httpSecurity.exceptionHandling(exception->exception.accessDeniedPage("/errorPage"));
        httpSecurity.authorizeHttpRequests(authorize->authorize.anyRequest().authenticated());
        httpSecurity.userDetailsService(userDetailsServiceImplementation);
        httpSecurity.csrf(c->c.disable());
        return httpSecurity.build();

        //ki neskhdem mvc 3adi nkhalyoha active khater tpritecti a ki nekhdem jeton w bech ntesti na7yoha bech najmou ntasstiyu

    }
}
