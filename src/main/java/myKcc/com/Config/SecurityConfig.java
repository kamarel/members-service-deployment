/*
package myKcc.com.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for simplicity, configure it if needed
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/v1/members/**").permitAll() // Protect your endpoints
                        .anyRequest().permitAll() // Other requests are open
                );
        // Use JWT if necessary, or configure your authentication method

        return http.build();
    }
}*/
