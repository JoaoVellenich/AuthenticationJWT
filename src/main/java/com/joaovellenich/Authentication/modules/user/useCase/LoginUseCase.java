package com.joaovellenich.Authentication.modules.user.useCase;

import com.joaovellenich.Authentication.infra.security.TokenService;
import com.joaovellenich.Authentication.modules.user.dto.LoginDTO;
import com.joaovellenich.Authentication.modules.user.dto.LoginResponseDTO;
import com.joaovellenich.Authentication.modules.user.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public LoginResponseDTO execute(LoginDTO dto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = this.tokenService.generateToken((UserEntity) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }
}
