package com.example.wheelworld.config;

import com.example.wheelworld.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("Basant")
//                .password(encoder.encode("Pwd1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("John")
//                .password(encoder.encode("Pwd2"))
//                .roles("USER","ADMIN","HR")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/WheelWorld/welcome", "/WheelWorld/Utilisateur/inscription", "/WheelWorld/Utilisateur/Connexion", "/WheelWorld/ListeDesAnnonces", "/WheelWorld/Vente", "/WheelWorld/Location", "/WheelWorld/ajouterFerme", "/WheelWorld/ajouterAppartement", "/WheelWorld/ajouterImmeuble", "/WheelWorld/ajouterLocal", "/WheelWorld/ajouterMaison", "/WheelWorld/ajouterTerrain", "/WheelWorld/supprimerAnnonce/{id}", "/WheelWorld/annonce/{id}","/WheelWorld/appartement/{id}","/WheelWorld/maison/{id}","/WheelWorld/trierVenteParPrixASC","/WheelWorld/trierVenteParPrixDESC","/WheelWorld/trierLocationParPrixASC","/WheelWorld/trierLocationParPrixDESC","/WheelWorld/local/{id}","/WheelWorld/terrain/{id}","/WheelWorld/ferme/{id}","/WheelWorld/immeuble/{id}","/WheelWorld/ListeDesUtilisateurs","/WheelWorld/SupprimerUtilisateur/{id}","/WheelWorld/trierVenteParPrixASC","/WheelWorld/trierVenteParPrixDESC","/WheelWorld/trierLocationParPrixASC","/WheelWorld/trierLocationParPrixDESC","/WheelWorld/{userId}/block","/WheelWorld/{userId}/unblock","/WheelWorld/ModifierUtilisateur","/WheelWorld/ModifierAppratement/{id}","/WheelWorld/ModifierFerme/{id}","/WheelWorld/ModifierImmeuble/{id}","/WheelWorld/ModifierLocal/{id}","/WheelWorld/ModifierMaison/{id}","/WheelWorld/ModifierTerrain/{id}")
                .permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/WheelWorld/**")
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
