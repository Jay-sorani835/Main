package com.example.yamldemo.service.impl;

import com.example.yamldemo.dto.JwtAuthResponse;
import com.example.yamldemo.dto.LoginDto;
import com.example.yamldemo.dto.RegisterDto;
import com.example.yamldemo.exception.ResourceNotFoundException;
import com.example.yamldemo.model.User;
import com.example.yamldemo.model.enums.Role;
import com.example.yamldemo.repository.UserRepository;
import com.example.yamldemo.security.JwtTokenProvider;
import com.example.yamldemo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {
        // check username exists in database
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new ResourceNotFoundException("Username is already taken!");
        }

        // check email exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ResourceNotFoundException("Email is already taken!");
        }

        User user = User.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(Role.USER) // By default all fresh signups are users
                .build();

        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            return new JwtAuthResponse(token);
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
