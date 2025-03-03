package com.cinema.service;

import com.cinema.api.model.Film.StateEnum;
import com.cinema.api.model.Showtime;
import com.cinema.repository.ShowtimeRepository;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@ApplicationScoped
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public Showtime save(Showtime showtime) {
        showtimeRepository.persist(showtime);
        return showtime;
    }

    public List<Showtime> findByDate(LocalDate date) {
        ZonedDateTime startOfDay = date.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        return showtimeRepository.findByFilmStateEqualsAndStartTimeBetweenOrderByStartTimeAsc(
                StateEnum.ACTIVE, startOfDay, endOfDay);
    }

}
