package com.events.web;

import com.events.model.Category;
import com.events.model.Event;
import com.events.service.CategoryService;
import com.events.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final CategoryService categoryService;


    public EventController(EventService eventService, CategoryService categoryService) {
        this.eventService = eventService;
        this.categoryService = categoryService;
    }


    @GetMapping
    public String getEventPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Event> events = this.eventService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("bodyContent", "events");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    public String editEventPage(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("event", event);
            model.addAttribute("bodyContent", "add-event");
            return "master-template";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @GetMapping("/add-form")
    public String addEventPage(Model model) {
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("event", new Event());
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-event");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(Event event,
                              BindingResult bindingResult,
                              @RequestParam MultipartFile image,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "add-product";
        }
        try {
            this.eventService.save(event, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/events";
    }

}
