package com.cinema.repository;


import java.time.ZonedDateTime;
import java.util.List;

import com.cinema.api.model.Showtime;
import com.cinema.api.model.Film.StateEnum;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface ShowtimeRepository extends PanacheRepository<Showtime> {
    List<Showtime> findByFilmStateEqualsAndStartTimeBetweenOrderByStartTimeAsc(StateEnum filmState, ZonedDateTime startTime, ZonedDateTime endTime);
}
