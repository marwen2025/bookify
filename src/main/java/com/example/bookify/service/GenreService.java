package com.example.bookify.service;

import com.example.bookify.dao.GenreRepository;
import com.example.bookify.entities.Author;
import com.example.bookify.entities.Genre;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreService implements IGenreService {
    private GenreRepository genreRepository;
    @Override
    public void saveGenre(Genre g){
    genreRepository.save(g);
    }
    @Override
    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }
    @Override
    public Page<Genre> getGenreByMc(String mc, Pageable p ){
         return genreRepository.findByNameContains(mc,p);
    }
    @Override
    public void editGenre(Long id, Genre editedGenre){
        Optional<Genre> existingGenreOptional = genreRepository.findById(id);
        if(existingGenreOptional.isPresent()){
            Genre existingGenre=existingGenreOptional.get();
            existingGenre.setName(editedGenre.getName());
            genreRepository.save(editedGenre);
        }
    }
    @Override
    public void deleteGenre(Long id){
        genreRepository.deleteById(id);
    }
    @Override
    public Genre getGenre(Long id){
        return genreRepository.findById(id).orElse(null);
    }
}
