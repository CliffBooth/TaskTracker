package bestsoftware.tasktracker.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {
    SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
        ).build();
    }
}
