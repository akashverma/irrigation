package com.example.irrigation.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailsService);
    }

    /**
     * 1. Spring security by default expects that passwords must not be in clear text. Rather they must be encrypted somehow.
     * Hence, we need to create a PasswordEncoder bean for spring-security to get enabled successfully in this project.
     * And also need to tell spring that the password for User (foo, foo)  does not need to be encrypted (to override its default expectation)
     *
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //2. But for the sake of this project simplicity we use NoOperation password encoder.
        // It means we are telling spring to use the password in the plain text format
        // without getting into mumbo-jumbo of actually encrypting the password
        return NoOpPasswordEncoder.getInstance();
    }


    /**
     * Must create this Bean to prevent error
     *
     * Field authenticationManager in com.akash.springsecurityjwt.controllers.HelloResource required a bean of type
     * 'org.springframework.security.authentication.AuthenticationManager' that could not be found.
     * The injection point has the following annotations:
     * 	- @org.springframework.beans.factory.annotation.Autowired(required=true)
     *
     * Action:
     * Consider defining a bean of type 'org.springframework.security.authentication.AuthenticationManager' in your configuration.
     *
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate").permitAll().
                anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
}


