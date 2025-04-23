package com.boot.SpringBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.boot.SpringBoot.crypto.CustomPasswordEncoder;
import com.boot.SpringBoot.service.CustomUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// @SuppressWarnings("unused")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(CsrfConfigurer::disable) // Disable CSRF for this example (handle it properly in production)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow OPTIONS requests without authentication
                        .anyRequest().authenticated()) // All other requests require authentication
                .formLogin(withDefaults())
                .httpBasic(withDefaults())// Enable Basic Authentication
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)); 

        return http.build();
    }

    // @SuppressWarnings("deprecation")
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     List<GrantedAuthority> authorities = new ArrayList<>(1);
    //     authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

    //     List<UserDetails> users = new ArrayList<>();
    //     users.add(new User("u1", "{noop}p1", authorities));
    //     users.add(new User("u2", "{noop}p2", authorities));

    //     UserDetails user3 = User.withDefaultPasswordEncoder()
    //             .username("u3")
    //             .password("p3")
    //             .roles("ADMIN")
    //             .build();

    //     UserDetails user4 = User.withDefaultPasswordEncoder()
    //             .username("u4")
    //             .password("p4")
    //             .roles("USER")
    //             .build();

    //     users.add(user3);
    //     users.add(user4);


    //     return new InMemoryUserDetailsManager(users);
    // }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(CustomPasswordEncoder.getInstance());
        provider.setUserDetailsService(customUserDetailsService);

        return provider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*")); // Allow all origins
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Crucial for sending credentials
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // @Bean
    // public AuthenticationEntryPoint authenticationEntryPoint() {
    //     return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    // }
}