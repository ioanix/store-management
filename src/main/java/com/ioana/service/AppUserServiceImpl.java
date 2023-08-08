package com.ioana.service;

import com.ioana.exception.ProjectException;
import com.ioana.model.AppUser;
import com.ioana.model.dto.AuthenticationRequest;
import com.ioana.model.dto.AuthenticationResponse;
import com.ioana.repository.AppUserRepository;
import com.ioana.security.JwtService;
import com.ioana.security.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse addUser(AppUser appUser) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByUsername(appUser.getUsername());

        if (optionalAppUser.isPresent()) {
            throw new ProjectException("There is already an account registered with this username");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);

        var jwtToken = jwtService.generateToken(new SecurityUser(appUser));

        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        var user = appUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("The user was not found"));

        var jwtToken = jwtService.generateToken(new SecurityUser(user));

        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }
}
