package com.example.bookify.service;


import com.example.bookify.entities.Genre;

import java.util.List;

public interface IGenreService {



    public void saveGenre(Genre g);
    public List<Genre> getAllGenres();
    public List<Genre> getGenreByMc(String mc);
    public void deleteGenre(Long id);
    public Genre getGenre(Long id);
}
