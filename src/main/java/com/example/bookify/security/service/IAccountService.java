package com.example.bookify.security.service;

import com.example.bookify.security.entities.AppUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IAccountService {


    public void addRole(String role);
    public void addUser(String username,String password, String email);
    public void addRoleToUser(String username, String role);
    public AppUser loadUserByUserName(String username);
}
