package com.example.bookify;

import com.example.bookify.dao.AuthorRepository;
import com.example.bookify.dao.BookRepository;
import com.example.bookify.dao.GenreRepository;
import com.example.bookify.entities.Author;
import com.example.bookify.security.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

//@Bean
    CommandLineRunner commandLineRunner(IAccountService accountService) {
        return args -> {
            accountService.addRole("USER");
            accountService.addRole("ADMIN");
            accountService.addUser("user", "123", "user@gmail.com");
            accountService.addUser("admin","123","admin@gmail.com");
            accountService.addRoleToUser("user","USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("admin", "USER");

        };
    }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


}
