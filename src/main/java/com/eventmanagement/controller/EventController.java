package com.eventmanagement.controller;

import com.eventmanagement.entity.Event;
import com.eventmanagement.payload.AllEventDto;
import com.eventmanagement.payload.EventDto;
import com.eventmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController{
    @Autowired
    EventService eventService;

    @PostMapping("/{userId}")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto, @PathVariable long userId)
    {
        EventDto edto = eventService.createEvent(eventDto, userId);
        return new ResponseEntity<>(edto, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<EventDto>getEventDetailById(@RequestParam long eventId)
    {
        EventDto eventDto = eventService.getEventDetailById(eventId);
        return new ResponseEntity<>(eventDto,HttpStatus.OK);
    }
    @GetMapping("/allEvents")
    public List<AllEventDto> getAllEvents()
    {
        List<AllEventDto> allEvents = eventService.findAllEvents();
        return allEvents;

    }

}
