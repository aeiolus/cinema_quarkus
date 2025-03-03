package com.cinema.api;

import java.sql.Date;
import java.util.List;

import com.cinema.api.model.Film;
import com.cinema.api.model.Film.StateEnum;
import com.cinema.service.FilmService;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;

@NonBlocking
@RunOnVirtualThread
public class FilmsResource implements FilmsApi {

    private final FilmService filmService;

    public FilmsResource(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public Response activateFilm(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateFilm'");
    }

    @Override
    public Response addFilm(@Valid @NotNull Film film) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFilm'");
    }

    @Override
    public Response deactivateFilm(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deactivateFilm'");
    }

    @Override
    public Response deleteFilm(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFilm'");
    }

    @Override
    public Response getFilm(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFilm'");
    }

    @Override
    public Response listFilms() {
        return Response.ok(filmService.getAllFilms()).build();
        // return Response.ok(List.of(new Film(1L, "Fight club", "Don't talk about it", StateEnum.ACTIVE, new Date(0), new Date(0)))).build();
    }

    @Override
    public Response updateFilm(Long id, @Valid @NotNull Film film) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFilm'");
    }
    
}