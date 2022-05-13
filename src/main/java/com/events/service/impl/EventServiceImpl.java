package com.events.service.impl;

import com.events.model.Category;
import com.events.model.Event;
import com.events.model.dto.EventDto;
import com.events.model.exceptions.CategoryNotFoundException;
import com.events.model.exceptions.EventNotFoundException;
import com.events.repository.jpa.CategoryRepository;
import com.events.repository.jpa.EventRepository;
import com.events.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    public EventServiceImpl(EventRepository eventRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public Optional<Event> findByName(String name) {
        return this.eventRepository.findByName(name);
    }

    @Override
    public Optional<Event> save(String name, String location, LocalDateTime dateCreated, Integer nrParticipants, Long categoryId, MultipartFile image) throws IOException {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Event event = new Event(name, location, dateCreated, nrParticipants, category);
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            event.setImageBase64(base64Image);
        }

        this.eventRepository.deleteByName(name);
        return Optional.of(this.eventRepository.save(event));
    }
    @Override
    public Event save(Event event, MultipartFile image) throws IOException {
        Category category = this.categoryRepository.findById(event.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException(event.getCategory().getId()));
        event.setCategory(category);
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            event.setImageBase64(base64Image);
        }
        return this.eventRepository.save(event);
    }


    @Override
    public Optional<Event> save(EventDto eventDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Event> edit(Long id,String name, String location, LocalDateTime dateCreated, Integer nrParticipants, Long categoryId, MultipartFile image) throws IOException {
        Event event = this.eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        event.setName(name);
        event.setLocation(location);
        event.setDateCreated(dateCreated);
        event.setNrParticipants(nrParticipants);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        event.setCategory(category);

        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            event.setImageBase64(base64Image);
        }
        return Optional.of(this.eventRepository.save(event));
    }

    @Override
    public Optional<Event> edit(Long id, EventDto eventDto) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }
}
