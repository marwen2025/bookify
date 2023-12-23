package com.example.bookify;

import com.example.bookify.dao.AuthorRepository;
import com.example.bookify.dao.BookRepository;
import com.example.bookify.dao.GenreRepository;
import com.example.bookify.entities.Author;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class BookifyApplication {

    public static void main(String[] args) {

        SpringApplication.run(BookifyApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository){
        return args -> {

        };
    }


}
