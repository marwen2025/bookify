package com.example.bookify.service;


import com.example.bookify.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGenreService {



    public void saveGenre(Genre g);
    public List<Genre> getAllGenres();
    public void deleteGenre(Long id);
    public Genre getGenre(Long id);
    public void editGenre(Long id, Genre editedGenre);
    public Page<Genre> getGenreByMc(String mc, Pageable p );
}
