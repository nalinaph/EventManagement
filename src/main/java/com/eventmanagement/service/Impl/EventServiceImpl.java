package com.eventmanagement.service.Impl;

import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.User;
//import com.eventmanagement.payload.AllEventDto;
import com.eventmanagement.exception.ResourceNotFoundException;
import com.eventmanagement.payload.EventDto;
import com.eventmanagement.payload.UserDto;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {


    EventRepository eventRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;



    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public EventDto createEvent(EventDto eventDto, long userId) {
        User user = userRepository.findById(userId).orElseThrow(
         ()-> new ResourceNotFoundException("User does not exist with ID:  "+userId));
        Event event = new Event();
        event = mapToEntity(eventDto);
        //  event.setUser(user);

        Event eventsaved = eventRepository.save(event);
        return mapToDto(eventsaved);
    }

    @Override
    public EventDto getEventDetailById(long eventId) {
        Event event = new Event();
        event = eventRepository.findById(eventId).orElseThrow(
                ()-> new ResourceNotFoundException("Event not found with Id "+eventId));
        return  mapToDto(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> allEvents = eventRepository.findAll();
        List<EventDto> list = allEvents.stream().map(this::mapToDto).toList();
        return list;    }

    @Override
    public List<EventDto> getEventsByDateAndDurationRange(LocalDate startDate, LocalDate endDate, int minDuration, int maxDuration) {
        List<Event> events = eventRepository.findByDateTimeAndDurationRange(startDate, endDate, minDuration, maxDuration);
        if(events!=null && !events.isEmpty())

            return events.stream().map(this::mapToDto).toList();
        else
        {
            return Collections.emptyList();

        }    }

    @Override
    public List<EventDto> getEventsByOrganizerAndAttendees(String organizer, int numberOfAttendees) {
        List<Event> list = eventRepository.findByOrganizerAndNumberOfAttendees(organizer, numberOfAttendees);
        if(list!=null && !list.isEmpty())

            return list.stream().map(this::mapToDto).toList();
        else
        {
            return Collections.emptyList();

        }    }

    @Override
    public List<Object[]> getEventsCountByUserAndDateInRange(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findEventsCountByUserAndDateInRange(startDate, endDate);
    }

    @Override
    public EventDto updateEvent(long eventId, EventDto updatedEventDto) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                ()-> new ResourceNotFoundException("EventId" +updatedEventDto +"does not exist to update"));
        if (updatedEventDto.getEventName() != null) {
            event.setEventName(updatedEventDto.getEventName());
        }
        if (updatedEventDto.getOrganizer() != null) {
            event.setOrganizer(updatedEventDto.getOrganizer());
        }

        if (updatedEventDto.getLocation() != null) {
            event.setLocation(updatedEventDto.getLocation());
        }
        if (updatedEventDto.getDateTime() != null) {
            event.setDateTime(updatedEventDto.getDateTime());
        }

        if (updatedEventDto.getDuration() != -1) {
            event.setDuration(updatedEventDto.getDuration());
        }
        Event updatedEvent = eventRepository.save(event);
        return mapToDto(updatedEvent);
    }


public Event mapToEntity(EventDto eventDto)
{
    Event event = modelMapper.map(eventDto, Event.class);
    return event;
}
public EventDto mapToDto(Event event)
{
    EventDto eventDto = modelMapper.map(event, EventDto.class);
    return eventDto;
}
}

