package com.example.bookify.controller;


import com.example.bookify.entities.Genre;
import com.example.bookify.service.GenreService;
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

public class GenreController {
    GenreService genreService;
    @GetMapping("/admin/genres")
    public String getAllGenres(Model m ,
                               @RequestParam(name ="mc",defaultValue = "") String mc,
                               @RequestParam(name="page",defaultValue = "0") int page,
                               @RequestParam(name ="size",defaultValue = "5") int size)
    {
        Page<Genre> l=genreService.getGenreByMc(mc, PageRequest.of(page , size));
        m.addAttribute("mc",mc);
        m.addAttribute("genres",l.getContent());
        m.addAttribute("pages",new int[l.getTotalPages()]);
        m.addAttribute("currentPage",l.getNumber());
        return "showGenres";
    }
    @GetMapping("admin/addGenre")
    public String addForm(Model m){
        m.addAttribute("genre",new Genre());
        return "addGenre";
    }
    @PostMapping("admin/addGenre")
    public String saveGenre(@Valid Genre g, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "addGenre";
        }
        genreService.saveGenre(g);
        return "redirect:/admin/genres";
    }
    @GetMapping("admin/genre/delete/{id}")
    public String deleteGenre(@PathVariable("id") Long idGenre){
        genreService.deleteGenre(idGenre);
        return "redirect:/admin/genres";
    }
    @GetMapping("admin/genre/edit/{id}")
    public String editForm(@PathVariable("id") Long id,Model m){
        Genre genre=genreService.getGenre(id);
        m.addAttribute("genre",genre);
        return "editGenre";
    }
    @PostMapping("admin/genre/edit/{id}")
    public String editGenre(@PathVariable("id") Long id, @ModelAttribute Genre editedGenre){
        genreService.editGenre(id,editedGenre);
        return "redirect:/admin/genres";
    }

}
