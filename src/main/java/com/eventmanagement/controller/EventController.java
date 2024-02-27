package com.eventmanagement.controller;

import com.eventmanagement.entity.Event;
//import com.eventmanagement.payload.AllEventDto;
import com.eventmanagement.payload.EventDto;
import com.eventmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
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
    public List<EventDto> getAllEvents()
    {
        List<EventDto> allEvents = eventService.findAllEvents();
        return allEvents;

    }
    @GetMapping("/eventsrange")
    public ResponseEntity<List<EventDto>> getEventsByDateAndDurationRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate,
            @RequestParam("minDuration") int minDuration,
            @RequestParam("maxDuration") int maxDuration) {
        List<EventDto> eventsByDateAndDurationRange = eventService.getEventsByDateAndDurationRange(startDate, endDate, minDuration, maxDuration);
       return new ResponseEntity<>(eventsByDateAndDurationRange,HttpStatus.OK);
    }
    @GetMapping("/filteredevents")
    public ResponseEntity<List<EventDto>> getEventsByOganizerAndAttendees(
            @RequestParam String organizer,@RequestParam int numberOfAttendees)
    {
        List<EventDto> eventsByAttendeesAndOrganizer = eventService.getEventsByOrganizerAndAttendees(organizer,numberOfAttendees);
        return new ResponseEntity<>(eventsByAttendeesAndOrganizer, HttpStatus.OK);
    }



}
