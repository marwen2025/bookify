package com.example.bookify.service;


import com.example.bookify.dao.AuthorRepository;
import com.example.bookify.dao.BookRepository;
import com.example.bookify.entities.Author;
import com.example.bookify.entities.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthorService implements IAuthorService{
    private AuthorRepository authorRepository;
    @Override
    public void saveAuthor(Author a){
        authorRepository.save(a);
    }
    @Override
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
    @Override
    public void editAuthor(Long id, Author editedAuthor){
        Optional<Author> existingAuthorOptional = authorRepository.findById(id);
        if(existingAuthorOptional.isPresent()){
            Author existingAuthor=existingAuthorOptional.get();
            existingAuthor.setName(editedAuthor.getName());
            existingAuthor.setBirthYear(editedAuthor.getBirthYear());
            authorRepository.save(existingAuthor);

        }
    }

    @Override
    public Page<Author> getAuthorByMc(String mc, Pageable b){
        return authorRepository.findByNameContains(mc,b);
    }
    @Override
    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }
    @Override
    public Author getAuthor(Long id){
        return authorRepository.findById(id).orElse(null);
    }

}
