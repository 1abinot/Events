package com.events.service;

import com.events.model.Event;
import com.events.model.dto.EventDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAll();

    Optional<Event> findById(Long id);

    Optional<Event> findByName(String name);

    Optional<Event> save(String name, String location, LocalDateTime dateCreated, Integer nrParticipants, Long categoryId, MultipartFile image) throws IOException;

    Optional<Event> save(EventDto eventDto);

    Event save(Event event, MultipartFile image) throws IOException;

    Optional<Event> edit(Long id, String name, String location, LocalDateTime dateCreated, Integer nrParticipants, Long categoryId, MultipartFile image) throws IOException;

    Optional<Event> edit(Long id, EventDto eventDto);

    void deleteById(Long id);
}
