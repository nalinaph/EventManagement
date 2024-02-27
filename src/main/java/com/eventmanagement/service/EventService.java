package com.eventmanagement.service;

import com.eventmanagement.entity.Event;
import com.eventmanagement.payload.AllEventDto;
import com.eventmanagement.payload.EventDto;

import java.util.List;

public interface EventService{
    EventDto createEvent(EventDto eventDto,long userId) ;
    EventDto getEventDetailById(long eventId);
    List<AllEventDto> findAllEvents();
}
