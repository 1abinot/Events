package com.events.repository.jpa;

import com.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByName(String name);

    Optional<Event> findByLocation(String location);

    void deleteByName(String name);
}
