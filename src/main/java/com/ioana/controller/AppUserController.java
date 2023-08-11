package com.ioana.controller;

import com.ioana.mapper.Mapper;
import com.ioana.model.AppUser;
import com.ioana.model.dto.AppUserDto;
import com.ioana.model.dto.AuthenticationRequest;
import com.ioana.model.dto.AuthenticationResponse;
import com.ioana.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;
    private final Mapper<AppUser, AppUserDto> appUserMapper;

    @PostMapping(path = "/users/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AppUserDto appUserDto) {
        log.info("appUserDto = {}", appUserDto);

        return ResponseEntity.ok(appUserService.addUser(appUserMapper.convertDtoToModel(appUserDto)));
    }

    @PostMapping("/users/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        log.info("request = {}", authenticationRequest);

        return ResponseEntity.ok(appUserService.authenticate(authenticationRequest));
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUserDto>> showAllUsers() {
        log.info("appUserList = {}", appUserService.getAll());

        return new ResponseEntity<>(appUserMapper.convertModelsToDtos(appUserService.getAll()), HttpStatus.OK);
    }
}
