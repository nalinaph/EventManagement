package com.eventmanagement.service;

import com.eventmanagement.entity.Event;
//import com.eventmanagement.payload.AllEventDto;
import com.eventmanagement.payload.EventDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService{
    EventDto createEvent(EventDto eventDto,long userId) ;
    EventDto getEventDetailById(long eventId);
    List<EventDto> findAllEvents();
    List<EventDto> getEventsByDateAndDurationRange(LocalDateTime startDate, LocalDateTime endDate, int minDuration, int maxDuration);

    List<EventDto> getEventsByOrganizerAndAttendees(String organizer,int numberOfAttendees);
}
