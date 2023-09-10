package com.example.eazyschool.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    // permit all requests
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain( HttpSecurity httpSecurity ) throws Exception{
//        httpSecurity.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
//                .formLogin( Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
//    }

//    denyAll requests -> to temporarily retire a specific API
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain( HttpSecurity httpSecurity ) throws Exception{
//        httpSecurity.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
//                .formLogin( Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
//    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain( HttpSecurity httpSecurity ) throws Exception {
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers( "/dashboard" ).authenticated()
                        .requestMatchers( "","/","/home" ).permitAll()
                        .requestMatchers( "/holidays/**" ).permitAll()
                        .requestMatchers( "contact" ).permitAll()
                        .requestMatchers( "/courses" ).permitAll()
                        .requestMatchers( "/saveMsg" ).permitAll()
                        .requestMatchers( "/about" ).permitAll()
                        .requestMatchers( "/assets/**" ).permitAll()
                )
                // default login
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
                // adding custom login page
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage( "/login" )
                        .defaultSuccessUrl( "/dashboard" )
                        .failureUrl( "/login?error=true" )
                        .permitAll())
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutSuccessUrl( "/login?logout=true" )
                        .invalidateHttpSession( true )
                        .permitAll())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    // In memory authentication - creating multiple users
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User
                .withUsername( "user" )
                .password( passwordEncoder().encode( "1234" )  )
//                .password( "1234" )
                .roles( "USER" )
                .build();

        UserDetails admin = User
                .withUsername( "admin" )
                .password( passwordEncoder().encode( "1234" ) )
//                .password( "1234" )
                .roles( "USER", "ADMIN" )
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
