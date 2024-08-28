
package Sukh.Assignment3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("Sukh")
                .password(passwordEncoder().encode("Sukhman"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("Nav")
                .password(passwordEncoder().encode("Navraj"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("King")
                .password(passwordEncoder().encode("12345678"))
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user1, user2);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable
                        )
                )
                .sessionManagement(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/book/add/**").hasRole("ADMIN")
                        .requestMatchers("/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(ex -> ex.accessDeniedHandler(new LoggingAccessDeniedHandler()))
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .successForwardUrl("/")
                        .failureForwardUrl("/login")
                        .permitAll())
                .logout((logout) -> logout.logoutSuccessUrl("/login"))
                .build();
    }
}

