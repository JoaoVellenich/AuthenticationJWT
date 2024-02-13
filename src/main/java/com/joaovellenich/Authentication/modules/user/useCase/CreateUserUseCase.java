package com.joaovellenich.Authentication.modules.user.useCase;

import com.joaovellenich.Authentication.modules.user.UserRoles;
import com.joaovellenich.Authentication.modules.user.dto.RegisterDTO;
import com.joaovellenich.Authentication.modules.user.entities.UserEntity;
import com.joaovellenich.Authentication.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
    @Autowired
    private UserRepository userRepository;
    public void execute(RegisterDTO dto) throws Exception {
        if(this.userRepository.findByEmail(dto.email()) != null) throw new Exception(("User already exists"));

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        var user = UserEntity.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encryptedPassword)
                .role(dto.role())
                .build();
        this.userRepository.save(user);
    }
}
