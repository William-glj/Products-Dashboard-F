package com.example.simpleProducts.security;


import org.springframework.context.annotation.Configuration;

// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

    /*
@Configuration
@EnableWebSecurity
public class SecurityClash {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService (PasswordEncoder encoder){



        UserDetails admin = User
                .builder()
                .username("Administrador")
                //.passwordEncoder(encoder::encode).password("admin123")
                .password(encoder.encode("admin"))
                .roles("Administrador")
                .build();

        return new InMemoryUserDetailsManager(admin);

    }





    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http)  throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/api/User/verifierUser").hasRole(Rol.Administrador.toString())
                       // .requestMatchers("/api/User/allUsers/vone").hasRole(Rol.Administrador.toString())
                        .requestMatchers("/api/User/allUsers/vtwo").hasRole(Rol.Administrador.toString())
                        .requestMatchers("/api/User/allUsers/vone").permitAll()

                )
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout.permitAll());

        return http.build();
    }



}
*/