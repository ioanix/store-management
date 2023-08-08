package com.ioana.mapper;

import com.ioana.model.AppUser;
import com.ioana.model.dto.AppUserDto;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper extends AbstractMapper<AppUser, AppUserDto> {

    @Override
    public AppUser convertDtoToModel(AppUserDto appUserDto) {
        return new AppUser(appUserDto.getFirstName(),
                appUserDto.getLastName(),
                appUserDto.getUsername(),
                appUserDto.getEmail(),
                appUserDto.getPassword(),
                appUserDto.getRole());
    }

    @Override
    public AppUserDto convertModelToDto(AppUser appUser) {
        return new AppUserDto(appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getRole());
    }
}
