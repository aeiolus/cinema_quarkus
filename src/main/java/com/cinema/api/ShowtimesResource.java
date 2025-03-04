package com.cinema.api;

import java.time.LocalDate;

import com.cinema.api.model.Showtime;
import com.cinema.service.ShowtimeService;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;

@RunOnVirtualThread
public class ShowtimesResource implements ShowtimesApi {

    private final ShowtimeService showtimeService;

    public ShowtimesResource(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @Override
    public Response createShowtime(@Valid @NotNull Showtime showtime) {
        return Response.ok(showtimeService.save(showtime)).build();
    }

    @Override
    public Response getShowtimesByDate(LocalDate date) {
        return Response.ok(showtimeService.findByDate(date)).build();
    }
    
}