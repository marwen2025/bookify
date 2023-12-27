package com.example.bookify.security.repository;

import com.example.bookify.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    public AppUser findAppUserByUsername(String userName);
}
