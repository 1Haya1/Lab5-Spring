package org.example.event.EventController;


import org.example.event.Api.ApiResponse;
import org.example.event.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final List<Event> events = new ArrayList<>();
    private int nextId = 1;

    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event) {
        event.setId(nextId++);
        events.add(event);
        return new ApiResponse("event added");
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return events;
    }


    @PutMapping("/update/{id}")
    public ApiResponse updateEvent(@PathVariable int id, @RequestBody Event updatedEvent) {
        for (Event event : events) {
            if (event.getId() == id) {
                event.setDescription(updatedEvent.getDescription());
                event.setCapacity(updatedEvent.getCapacity());
                event.setStartDate(updatedEvent.getStartDate());
                event.setEndDate(updatedEvent.getEndDate());
                return new ApiResponse("event update");
            }
        }
        return new ApiResponse("event not found");
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteEvent(@PathVariable int id) {
        if (events.removeIf(event -> event.getId() == id)) {
            return new ApiResponse("event with Id " + id + " deleted");
        } else {
            return new ApiResponse("event not found");
        }
    }


    @GetMapping("/search/{id}")
    public Event searchEventById(@PathVariable int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }
}



