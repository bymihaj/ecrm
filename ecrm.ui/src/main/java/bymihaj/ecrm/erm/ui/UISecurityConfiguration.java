package bymihaj.ecrm.erm.ui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UISecurityConfiguration {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .antMatchers("/*").permitAll()
            .antMatchers("/*/*").permitAll()
            .anyRequest().hasRole("TEACHER").and().formLogin().permitAll();
        return http.build();
    }
    
    @SuppressWarnings("deprecation")
    @Bean
    public UserDetailsService userDetailService() {
        UserDetails teacher = User.withDefaultPasswordEncoder().username("teacher").password("teacher").roles("TEACHER").build();
        return new InMemoryUserDetailsManager(teacher);
    }

}
