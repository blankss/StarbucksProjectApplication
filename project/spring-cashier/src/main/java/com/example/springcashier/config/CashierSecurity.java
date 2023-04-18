package com.example.springcashier.config;

import com.example.springcashier.register.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class CashierSecurity {

    @Autowired
    private CustomLoginSucessHandler sucessHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            // URL matching for accessibility
            .antMatchers("/", "/login", "/register", "/h2-console/**").permitAll()
            .antMatchers("/user/**").hasAnyAuthority("USER")
            .anyRequest().authenticated()
            .and()
            // form login
            .csrf().disable().formLogin()
            .loginPage("/login")
            .failureUrl("/login?error=true")
            .successHandler(sucessHandler)
            .usernameParameter("email")
            .passwordParameter("password")
            .and()
            // logout
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .and()
            .exceptionHandling()
            .accessDeniedPage("/access-denied");

        http.authenticationProvider(authenticationProvider());
        http.headers().frameOptions().sameOrigin();

        return http.build();
        // http.csrf()
        //     .ignoringAntMatchers("/h2-console/**")
        //     .disable(); // disable CSRF for POSTS
        // http.headers().frameOptions().disable();
        // http
        //     .authorizeHttpRequests((requests) -> requests
        //             .antMatchers("/", "/home", "/registration").permitAll()
        //             .anyRequest().authenticated()
        //     )
        //     .formLogin(withDefaults())
        //     .logout((logout) -> logout.permitAll());

        // return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user =
    //         User.withDefaultPasswordEncoder()
    //                 .username("user")
    //                 .password("password")
    //                 .roles("USER")
    //                 .build();

    //     return new InMemoryUserDetailsManager(user);
    // }
}