package com.example.bookify.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AppUser {
    @Id
    private String id;
    @Column(unique = true)
    private String username;
    private String password;
    private String mail;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;
}
