package com.example.bookify.service;

import com.example.bookify.entities.Book;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.io.IOException;
import java.util.List;

public interface IBookService {
    public void SaveBook(Book b, MultipartFile photo,MultipartFile book) throws IOException;
    public List<Book> getAllBooks();
    public Page<Book> getBookByMc(String mc , Pageable t);
    public List<Book> getBookByAuth(Long idAuth);
    public void deleteBook(Long id);
    public Book getBook(Long id);


    public void editBook(Long id, Book editedBook,MultipartFile photo,MultipartFile book) throws IOException;
}
