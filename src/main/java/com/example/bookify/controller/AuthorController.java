package com.example.bookify.controller;

import com.example.bookify.entities.Author;
import com.example.bookify.entities.Book;
import com.example.bookify.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class AuthorController
{
    AuthorService authorService;
    @GetMapping("/")
    public String home()
    {
        return "redirect:/admin/books";
    }
    @GetMapping("/admin/authors")
    public String getAllAuthors(Model m,
                                @RequestParam(name ="mc",defaultValue = "") String mc,
                                @RequestParam(name="page",defaultValue = "0") int page,
                                @RequestParam(name ="size",defaultValue = "5") int size)
    {
        Page<Author> l=authorService.getAuthorByMc(mc, PageRequest.of(page,size));
        m.addAttribute("mc",mc);
        m.addAttribute("authors",l.getContent());
        m.addAttribute("pages",new int[l.getTotalPages()]);
        m.addAttribute("currentPage",l.getNumber());
        return "showAuthors";
    }
    @GetMapping("admin/addAuthor")
    public String addForm(Model m)
    {
        m.addAttribute("author", new Author());
        return "addAuthor";
    }
    @PostMapping("admin/addAuthor")
    public String saveAuthor(@Valid Author a, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addAuthor";
        }
        authorService.saveAuthor(a);
        return "redirect:/admin/authors";
    }
    @GetMapping("admin/author/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long idAuthor)
    {
        authorService.deleteAuthor(idAuthor);
        return "redirect:/admin/authors";
    }
    @GetMapping("/admin/author/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model ) {
        Author author = authorService.getAuthor(id);
        model.addAttribute("author", author);
        return "editAuthor";
    }

    @PostMapping("/admin/author/edit/{id}")
    public String editAuthor(@PathVariable("id") Long id, @ModelAttribute Author editedAuthor){
        authorService.editAuthor(id, editedAuthor);
        return "redirect:/admin/authors";
    }
}
