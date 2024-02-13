package com.joaovellenich.Authentication.modules.user.controller;

import com.joaovellenich.Authentication.modules.user.dto.LoginDTO;
import com.joaovellenich.Authentication.modules.user.dto.LoginResponseDTO;
import com.joaovellenich.Authentication.modules.user.dto.RegisterDTO;
import com.joaovellenich.Authentication.modules.user.useCase.CreateUserUseCase;
import com.joaovellenich.Authentication.modules.user.useCase.LoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private LoginUseCase loginUseCase;
    @Autowired
    private CreateUserUseCase createUserUseCase;


    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginDTO loginDTO){
        LoginResponseDTO responseDTO = this.loginUseCase.execute(loginDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO){
        try{
            this.createUserUseCase.execute(registerDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/adminRoute")
    public String adminRoute(){
        return "You are an admin";
    }

    @GetMapping("/userRoute")
    public String userRoute(){return "you are a user";}
}
