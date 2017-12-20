package com.javamentor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().ignoringAntMatchers("/h2-console").disable()
                .formLogin().loginPage("/login").permitAll()
                .and().authorizeRequests().antMatchers("/").permitAll()
                .and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and().authorizeRequests().antMatchers("/users/**").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers("/roles/**").hasAnyAuthority("USER", "ADMIN")
                .and().exceptionHandling().accessDeniedPage("/access-denied")
                .and().headers().frameOptions().disable();

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);

        return provider;

    }

    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder builder) {

        builder.authenticationProvider(authenticationProvider);

    }

}
