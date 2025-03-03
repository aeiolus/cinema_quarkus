package com.cinema.repository;

import java.time.ZonedDateTime;
import java.util.List;

import com.cinema.api.model.Showtime;
import com.cinema.api.model.Film.StateEnum;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ShowtimeRepository implements PanacheRepository<Showtime> {
    
    
    public List<Showtime> findByFilmStateEqualsAndStartTimeBetweenOrderByStartTimeAsc(StateEnum filmState, ZonedDateTime startTime, ZonedDateTime endTime) {
        return find("film.state = ?1 AND startTime >= ?2 AND startTime <= ?3 ORDER BY startTime ASC", 
                  filmState, startTime, endTime).list();
    }
}
