package com.ecommerce.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AdminConfiguration  {

    @Bean
    public UserDetailsService userDetailsService(){
        return new AdminServiceConfig();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests().antMatchers("/*").permitAll()
//                .antMatchers("/admin/*")
//                .hasAuthority("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/do-login")
//                .defaultSuccessUrl("/admin/index")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();
//    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Allow access to static resources
                        .requestMatchers("/js/**", "/css/**", "/images/**").permitAll()
                        // Allow access to the root and other non-admin paths
                        .requestMatchers("/**").permitAll()
                        // Restrict access to /admin paths to users with "ADMIN" authority
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Custom login page
                        .loginProcessingUrl("/do-login")  // URL for processing login form submission
                        .defaultSuccessUrl("/index")  // Default URL on successful login
                        .permitAll()
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))  // Configures the logout URL
                        .logoutSuccessUrl("/login?logout")  // Redirect URL after successful logout
                        .permitAll()
                );

        return http.build();
    }

}
