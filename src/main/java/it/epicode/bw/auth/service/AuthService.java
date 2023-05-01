package it.epicode.bw.auth.service;

import it.epicode.bw.auth.payload.LoginDto;
import it.epicode.bw.auth.payload.RegisterDto;

public interface AuthService {
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
