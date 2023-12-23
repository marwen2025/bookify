package com.example.bookify.dao;

import com.example.bookify.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    public Page<Book> findByTitleContains(String mc, Pageable b);
    @Query("select p from Book p where p.author.id=:x")
    public List<Book> getBooksByAuthor(@Param("x") Long idA);


}
