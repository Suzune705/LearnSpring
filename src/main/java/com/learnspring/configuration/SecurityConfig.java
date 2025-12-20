package com.learnspring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;



@Configuration
@EnableWebSecurity  // allow SecurityFilterChain run before each requests
@EnableMethodSecurity  // security by method
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINTS = {"/user/getAll", "/user/log-in", "/user/search/**","/user/create", "/role/create"};

    @Value("${jwt.signerKey}")
    private String signerKey ;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity ) throws Exception{

        AuthenticationEntryPoint authenticationEntryPoint = new JwtAuthenticationEntryPoint();
        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers(PUBLIC_ENDPOINTS).permitAll()      // hasRole read SecurityContextHolder
                        .anyRequest().permitAll()
                );
        // active JWT-Mode to verify signature of jwt
        httpSecurity.oauth2ResourceServer(oauth ->
                oauth.jwt(jwt -> jwt
                        .decoder(jwtDecoder())
                        .jwtAuthenticationConverter(jwtConverter()) // convert jwt

                ).authenticationEntryPoint(authenticationEntryPoint) // try catch 401 and 403

        );

        // Disable CSRF because JWT authentication does not rely on cookies or sessions
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
    @Bean
    JwtAuthenticationConverter jwtConverter(){
        // convert roles from payload
        JwtGrantedAuthoritiesConverter authorConverter = new JwtGrantedAuthoritiesConverter();
        authorConverter.setAuthorityPrefix("ROLE_");
        // combine jwt + authorities
        JwtAuthenticationConverter  authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(authorConverter);
        return  authenticationConverter;
     }

    // verify signature
    @Bean
    JwtDecoder jwtDecoder(){
        // save signerKey with hs512 algo
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(StandardCharsets.UTF_8), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec) // use key
                .macAlgorithm(MacAlgorithm.HS512) // Security Constraint : only allows jwt that has algo = HS512 in header
                .build();
    }


}
