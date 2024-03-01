package com.eventmanagement.service;

import com.eventmanagement.entity.Event;
//import com.eventmanagement.payload.AllEventDto;
import com.eventmanagement.payload.EventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    EventDto createEvent(EventDto eventDto, long userId);

    EventDto getEventDetailById(long eventId);

    List<EventDto> findAllEvents();

    List<EventDto> getEventsByDateAndDurationRange(LocalDate startDate, LocalDate endDate, int minDuration, int maxDuration);

    List<EventDto> getEventsByOrganizerAndAttendees(String organizer, int numberOfAttendees);

    List<Object[]> getEventsCountByUserAndDateInRange(LocalDate startDate, LocalDate endDate);

    EventDto updateEvent(long eventId, EventDto updatedEventDto);

    void deleteEvent(long eventId);

}
