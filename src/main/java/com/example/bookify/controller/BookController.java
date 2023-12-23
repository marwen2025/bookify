package com.example.bookify.controller;

import com.example.bookify.entities.Book;
import com.example.bookify.service.AuthorService;
import com.example.bookify.service.BookService;
import com.example.bookify.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor

public class BookController {
    AuthorService authorService;
    BookService bookService;
    GenreService genreService;

    @GetMapping("/admin/books")
    public String getAllBooks(Model m,
                              @RequestParam(name ="mc",defaultValue = "") String mc,
                              @RequestParam(name="page",defaultValue = "0") int page,
                              @RequestParam(name ="size",defaultValue = "5") int size)
    {
        Page<Book> l=bookService.getBookByMc(mc, PageRequest.of(page,size));
        m.addAttribute("mc",mc);
        m.addAttribute("books",l.getContent());
        m.addAttribute("pages",new int[l.getTotalPages()]);
        m.addAttribute("currentPage",l.getNumber());
        return "showBooks";
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteBook(@PathVariable("id")Long idBook){
        bookService.deleteBook(idBook);
        return "redirect:/admin/books";
    }

}
