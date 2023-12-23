package com.example.bookify.service;

import com.example.bookify.entities.Author;

import java.util.List;

public interface IAuthorService {
    public void saveAuthor(Author a);
    public List<Author> getAllAuthors();
    public List<Author> getAuthorByMc(String mc);
    public void deleteAuthor(Long id);
    public Author getAuthor(Long id);
}

