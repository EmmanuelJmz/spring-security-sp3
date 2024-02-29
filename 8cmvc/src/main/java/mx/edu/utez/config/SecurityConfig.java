package mx.edu.utez.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails adolecente = User.withUsername("adolecente")
                .password(
                        passwordEncoder().encode("adolecente1")
                )
                .roles("ADOL")
                .build();

        UserDetails kids = User.withUsername("kids")
                .password(
                        passwordEncoder().encode("kid1")
                )
                .roles("KID")
                .build();

        UserDetails adulto = User.withUsername("adulto")
                .password(
                        passwordEncoder().encode("adulto1")
                )
                .roles("ADULTO")
                .build();

        return new InMemoryUserDetailsManager(adolecente, adulto, kids);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeHttpRequests((request) ->{
            request.requestMatchers("/","/index").permitAll();
            request.anyRequest().authenticated();
        });

        httpSecurity.formLogin((login)->{
            login.loginPage("/login").permitAll();
        });

        httpSecurity.logout((logout)->{
           logout.permitAll();
        });

        return httpSecurity.build();
    }
}
