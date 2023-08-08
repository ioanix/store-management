package com.ioana.service;

import com.ioana.model.AppUser;
import com.ioana.model.dto.AuthenticationRequest;
import com.ioana.model.dto.AuthenticationResponse;

import java.util.List;

public interface AppUserService {

    List<AppUser> getAll();

    AuthenticationResponse addUser(AppUser appUser);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
