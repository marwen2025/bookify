package com.example.bookify.service;

import com.example.bookify.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAuthorService {
    public void saveAuthor(Author a);
    public List<Author> getAllAuthors();
    public Page<Author> getAuthorByMc(String mc, Pageable b);
    public void deleteAuthor(Long id);
    public Author getAuthor(Long id);
    public void editAuthor(Long id, Author editedAuthor);
}

