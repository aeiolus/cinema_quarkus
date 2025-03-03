package com.cinema.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import com.cinema.api.model.Film;

@ApplicationScoped
@Transactional
@Blocking
public class FilmRepository implements PanacheRepository<Film> {
    
}
