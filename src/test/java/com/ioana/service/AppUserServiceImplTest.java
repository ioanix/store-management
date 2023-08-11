package com.ioana.service;

import com.ioana.exception.ProjectException;
import com.ioana.model.AppUser;
import com.ioana.model.dto.AuthenticationRequest;
import com.ioana.model.dto.AuthenticationResponse;
import com.ioana.repository.AppUserRepository;
import com.ioana.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppUserServiceImplTest {

    private static final String USERNAME = "username";
    private static final String NEW_USERNAME = "new_username";
    private static final String PASSWORD = "password";
    private static final String JWT = "jwt";

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AppUser appUser;

    @Mock
    private AppUser newUser;

    @Mock
    private AuthenticationRequest authenticationRequest;

    private AppUserServiceImpl underTest;

    @BeforeEach
    void setup() {

        underTest = new AppUserServiceImpl(appUserRepository, passwordEncoder, jwtService, authenticationManager);
    }

    @Test
    void shouldAddUser() {
        when(newUser.getUsername()).thenReturn(NEW_USERNAME);
        when(appUserRepository.findByUsername(newUser.getUsername())).thenReturn(Optional.empty());
        when(newUser.getPassword()).thenReturn(PASSWORD);
        when(jwtService.generateToken(any())).thenReturn(JWT);

        AuthenticationResponse authenticationResponse = underTest.addUser(newUser);

        verify(appUserRepository).save(newUser);

        assertEquals(authenticationResponse.getToken(), JWT);
    }

    @Test
    void shouldThrowExceptionIfUserAlreadyExists() {
        when(appUser.getUsername()).thenReturn(USERNAME);
        when(appUserRepository.findByUsername(appUser.getUsername())).thenReturn(Optional.of(appUser));

        assertThatThrownBy(() -> underTest.addUser(appUser))
                .isInstanceOf(ProjectException.class)
                .hasMessageContaining("There is already an account registered with this username");
    }

    @Test
    void shouldAuthenticateUser() {
        when(authenticationRequest.getUsername()).thenReturn(USERNAME);
        when(appUserRepository.findByUsername(authenticationRequest.getUsername())).thenReturn(Optional.of(appUser));
        when(jwtService.generateToken(any())).thenReturn(JWT);

        AuthenticationResponse authenticationResponse = underTest.authenticate(authenticationRequest);

        verify(authenticationManager).authenticate(any());

        assertEquals(authenticationResponse.getToken(), JWT);
    }

    @Test
    void shouldGetAllUsers() {
        underTest.getAll();

        verify(appUserRepository).findAll();
    }
}