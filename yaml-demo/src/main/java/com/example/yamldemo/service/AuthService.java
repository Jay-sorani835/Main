package com.example.yamldemo.service;

import com.example.yamldemo.dto.JwtAuthResponse;
import com.example.yamldemo.dto.LoginDto;
import com.example.yamldemo.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
