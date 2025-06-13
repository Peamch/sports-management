package com.sports.sports_management.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider();
        jwtTokenProvider.jwtSecret = "myverysecureandlongjwtsecretkey123456";
        jwtTokenProvider.jwtExpirationInMs = 3600000;
    }

    @Test
    void generateAndValidateToken() {
        User user = new User("testuser", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        String token = jwtTokenProvider.generateToken(auth);

        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
        assertEquals("testuser", jwtTokenProvider.getUsernameFromJWT(token));
    }

    @Test
    void invalidTokenShouldFailValidation() {
        assertFalse(jwtTokenProvider.validateToken("invalid.token.here"));
    }
}