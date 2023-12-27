package com.example.bookify.dao;

import com.example.bookify.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    public Page<Author> findByNameContains(String mc, Pageable b);

}
