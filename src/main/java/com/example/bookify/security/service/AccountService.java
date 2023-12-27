package com.example.bookify.security.service;


import com.example.bookify.security.entities.AppRole;
import com.example.bookify.security.entities.AppUser;
import com.example.bookify.security.repository.RoleRepository;
import com.example.bookify.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AccountService implements IAccountService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;



    @Override
    public void addRole(String role) {
        roleRepository.save(AppRole.builder().role(role).build());
    }

    @Override
    public void addUser(String username, String password, String mail){
        AppUser user=userRepository.findAppUserByUsername(username);
        if (user != null) throw new RuntimeException("user exist");
        userRepository.save(AppUser
                .builder()
                .id(UUID.randomUUID().toString())
                .mail(mail)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build());
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser user = loadUserByUserName(username);
        AppRole rol= roleRepository.findById(role).orElse(null);
        user.getRoles().add(rol);



    }

    @Override
    public AppUser loadUserByUserName(String username) {

        return userRepository.findAppUserByUsername(username);
    }

}
