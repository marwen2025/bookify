package com.example.bookify.security.repository;

import com.example.bookify.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole,String> {

}
