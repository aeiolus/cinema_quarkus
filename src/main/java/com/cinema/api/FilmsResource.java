package com.cinema.api;

import com.cinema.api.model.Film;
import com.cinema.api.model.Film.StateEnum;
import com.cinema.service.FilmService;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@RunOnVirtualThread
public class FilmsResource implements FilmsApi {

    private final FilmService filmService;

    public FilmsResource(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public Response activateFilm(Long id) {
        return filmService.getFilm(id)
                .map(film -> {
                    film.setState(StateEnum.ACTIVE);
                    filmService.saveFilm(film);
                    return Response.ok();
                })
                .orElse(Response.status(Status.NOT_FOUND)).build();
    }

    @Override
    public Response addFilm(@Valid @NotNull Film film) {
        film.setState(StateEnum.OPEN);
        return Response.ok(filmService.saveFilm(film)).build();
    }

    @Override
    public Response deactivateFilm(Long id) {
        return filmService.getFilm(id)
                .map(film -> {
                    film.setState(StateEnum.INACTIVE);
                    filmService.saveFilm(film);
                    return Response.ok();
                })
                .orElse(Response.status(Status.NOT_FOUND)).build();
    }

    @Override
    public Response deleteFilm(Long id) {
        filmService.deleteFilm(id);
        return Response.noContent().build();
    }

    @Override
    public Response getFilm(Long id) {
        return Response.ok(filmService.getFilm(id).orElseThrow(()
                -> new IllegalArgumentException("Film not found"))).build();
    }

    @Override
    public Response listFilms() {
        return Response.ok(filmService.getAllFilms()).build();
    }

    @Override
    public Response updateFilm(Long id, @Valid @NotNull Film film) {
        return Response.ok(filmService.saveFilm(film)).build();
    }
    
}