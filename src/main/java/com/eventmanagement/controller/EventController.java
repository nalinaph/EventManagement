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

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    EventService eventService;

    //  Create Event Method
    //http://localhost:8080/api/events?userId=1

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto, @RequestParam long userId) {
        EventDto edto = eventService.createEvent(eventDto, userId);
        return new ResponseEntity<>(edto, HttpStatus.CREATED);
    }

    // Get Event Detail By Id
    // API url example : http://localhost:8080/api/events?eventId=2

    @GetMapping
    public ResponseEntity<EventDto> getEventDetailById(@RequestParam long eventId) {
        EventDto eventDto = eventService.getEventDetailById(eventId);
        return new ResponseEntity<>(eventDto, HttpStatus.OK);
    }

    // Displays all the events in the table

    //API url :  http://localhost:8080/api/events/allEvents
    @GetMapping("/allEvents")
    public List<EventDto> getAllEvents() {
        List<EventDto> allEvents = eventService.findAllEvents();
        return allEvents;

    }

    // Get Events within particual Date and Duration range
    // API url example : http://localhost:8080/api/events/eventsrange?startDate=2024-01-21&endDate=2024-05-25&minDuration=6&maxDuration=17
    // replace Date and Duration as per data in the table
    @GetMapping("/eventsrange")
    public ResponseEntity<?> getEventsByDateAndDurationRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("minDuration") int minDuration,
            @RequestParam("maxDuration") int maxDuration) {
        List<EventDto> eventsByDateAndDurationRange = eventService.getEventsByDateAndDurationRange(startDate, endDate, minDuration, maxDuration);
        if (!eventsByDateAndDurationRange.isEmpty())
            return new ResponseEntity<>(eventsByDateAndDurationRange, HttpStatus.OK);
        else {
            String message = "No events found within the specified criteria.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }

    // Get events of particular Oraganizer and Number of attendees
    // API url example : http://localhost:8080/api/events/filteredevents?organizer=Ninada Events&numberOfAttendees=1510
    @GetMapping("/filteredevents")
    public ResponseEntity<?> getEventsByOganizerAndAttendees(
            @RequestParam String organizer, @RequestParam int numberOfAttendees) {
        List<EventDto> eventsByAttendeesAndOrganizer = eventService.getEventsByOrganizerAndAttendees(organizer, numberOfAttendees);
        if (!eventsByAttendeesAndOrganizer.isEmpty())
            return new ResponseEntity<>(eventsByAttendeesAndOrganizer, HttpStatus.OK);
        else {
            String message = "No events found within the specified criteria.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }

    // This method displays First Name, Last Name of user and date of event and attendees count
    // Need to modify this method
    // API url example : http://localhost:8080/api/events/events-count-by-user?startDate=2024-01-23&endDate=2024-05-25
    @GetMapping("/events-count-by-user")
    public List<Object[]> getEventsCountByUserAndDateInRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return eventService.getEventsCountByUserAndDateInRange(startDate, endDate);
    }

    // Updating Event fields : Event Name, Location, Organizer, DateTime, Duration

    // API url example : http://localhost:8080/api/events?eventId=2
    @PutMapping
    public ResponseEntity<EventDto> updateEvent(@RequestParam long eventId, @RequestBody EventDto updatedEventDto) {
        EventDto dto = eventService.updateEvent(eventId, updatedEventDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }


    // Delete Event Method
    //API url example : http://localhost:8080/api/events/2
    // Here replace number "2" with eventId which is stored in DB table
    @DeleteMapping("/{eventId}")

    public ResponseEntity<String> deleteEvent(@PathVariable long eventId) {
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>("Event is deleted", HttpStatus.OK);
    }


}
