package com.cinema.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import com.cinema.api.model.Film;

@ApplicationScoped
public class FilmRepository implements PanacheRepository<Film> {

    public Film merge(Film film) {
        return getEntityManager().merge(film);
    }
    
}
