package com.sports.sports_management.service;

import com.sports.sports_management.entity.Role;
import com.sports.sports_management.entity.User;
import com.sports.sports_management.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    @Test
    void loadUserByUsername_found() {
        UserRepository userRepository = mock(UserRepository.class);
        User user = new User("testuser", "password", "test@test.com");
        user.setRoles(Collections.singleton(new Role("ROLE_USER")));
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        CustomUserDetailsService service = new CustomUserDetailsService();
        service.userRepository = userRepository;

        UserDetails userDetails = service.loadUserByUsername("testuser");

        assertEquals("testuser", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void loadUserByUsername_notFound() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername("absent")).thenReturn(Optional.empty());

        CustomUserDetailsService service = new CustomUserDetailsService();
        service.userRepository = userRepository;

        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("absent"));
    }
}