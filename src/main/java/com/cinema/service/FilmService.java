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
    private final ShowtimeService showtimeService;

    public FilmService(FilmRepository filmRepository, ShowtimeService showtimeService) {
        this.filmRepository = filmRepository;
        this.showtimeService = showtimeService;
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll().list();
    }

    public Optional<Film> getFilm(Long id) {
        return Optional.ofNullable(filmRepository.findById(id));
    }

    public Film saveFilm(Film film) {
        if (film.getId() == null) {
            filmRepository.persist(film);
            return film;
        } else {
            return filmRepository.merge(film);
        }
    }

    public void deleteFilm(Long id) {
        showtimeService.deleteByFilm(id);
        filmRepository.deleteById(id);
    }
}
