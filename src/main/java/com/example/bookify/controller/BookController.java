package com.example.bookify.controller;

import com.example.bookify.entities.Book;
import com.example.bookify.service.AuthorService;
import com.example.bookify.service.BookService;
import com.example.bookify.service.GenreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    @GetMapping("/admin/book/delete/{id}")
    public String deleteBook(@PathVariable("id")Long idBook){
        bookService.deleteBook(idBook);
        return "redirect:/admin/books";
    }
    @GetMapping("/admin/addBook")
    public String addForm(Model m)
    {
        m.addAttribute("book",new Book());
        m.addAttribute("genres",genreService.getAllGenres());
        m.addAttribute("authors",authorService.getAllAuthors());
        return "addBook";
    }
    @PostMapping("admin/addBook")
    public String saveBook(@Valid Book p, BindingResult bindingResult, Model m, @RequestParam("image")MultipartFile photo,@RequestParam("pdf") MultipartFile pdf) throws IOException{
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            m.addAttribute("genres",genreService.getAllGenres());
            m.addAttribute("author",authorService.getAllAuthors());
            return "addBook";
        }

        bookService.SaveBook(p,photo,pdf);
        return "redirect:/admin/books";
    }
    @GetMapping("/admin/book/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model ) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("author",authorService.getAllAuthors());
        return "editBook";
    }

    @PostMapping("/admin/book/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, @ModelAttribute Book editedBook, @RequestParam("image") MultipartFile photo,@RequestParam("pdf") MultipartFile pdf) throws IOException {
        bookService.editBook(id, editedBook, photo,pdf);
        return "redirect:/admin/books";
    }

}
