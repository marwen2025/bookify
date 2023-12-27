package com.example.bookify.dao;

import com.example.bookify.entities.Author;
import com.example.bookify.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
    public Page<Genre> findByNameContains(String mc, Pageable b);
}
