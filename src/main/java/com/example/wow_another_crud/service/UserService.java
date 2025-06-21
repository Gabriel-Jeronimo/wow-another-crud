package com.example.wow_another_crud.service;

import com.example.wow_another_crud.config.SecurityConfig;
import com.example.wow_another_crud.dto.CreateUserDto;
import com.example.wow_another_crud.dto.LoginUserDto;
import com.example.wow_another_crud.dto.RecoveryJwtTokenDto;
import com.example.wow_another_crud.infraestructure.security.JwtTokenService;
import com.example.wow_another_crud.model.User;
import com.example.wow_another_crud.model.UserDetailsImpl;
import com.example.wow_another_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());
        System.out.println(loginUserDto);

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            System.out.println(authentication.isAuthenticated());

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));


    }

    public void createUser(CreateUserDto createUserDto) {
        User newUser = new User(createUserDto.email(), securityConfig.passwordEncoder().encode(createUserDto.password()));
        this.userRepository.save(newUser);
    }
}
