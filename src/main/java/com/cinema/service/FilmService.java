package com.cinema.service;

import com.cinema.api.model.Film;
import com.cinema.repository.FilmRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll().list();
    }

    public Optional<Film> getFilm(Long id) {
        return Optional.ofNullable(filmRepository.findById(id));
    }

    public Film saveFilm(Film film) {
        filmRepository.persist(film);
        return film;
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }
}
