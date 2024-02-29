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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
   User user = userRepository.findById(userId).get();
       Event event = new Event();
       event = mapToEntity(eventDto);
        event.setUser(user);

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
//    public List<AllEventDto> findAllEvents()
//    {
//        return eventRepository.findAllEvents();
//    }
    public List<EventDto> findAllEvents()
    {
        List<Event> allEvents = eventRepository.findAll();
        List<EventDto> list = allEvents.stream().map(this::mapToDto).toList();
        return list;
    }

    @Override
    public List<EventDto> getEventsByDateAndDurationRange(LocalDate startDate, LocalDate endDate, int minDuration, int maxDuration) {

        List<Event> events = eventRepository.findByDateTimeAndDurationRange(startDate, endDate, minDuration, maxDuration);
       if(events!=null)

        return events.stream().map(this::mapToDto).toList();
       else
           return null;
    }



    public List<EventDto> getEventsByOrganizerAndAttendees(String organizer,int numberOfAttendees)
    {
        List<Event> list = eventRepository.findByOrganizerAndNumberOfAttendees(organizer, numberOfAttendees);
        List<EventDto> allEventsByOrgAtten = list.stream().map(this::mapToDto).toList();
        return allEventsByOrgAtten;
    }
    public List<Object[]> getEventsCountByUserAndDateInRange(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findEventsCountByUserAndDateInRange(startDate, endDate);
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
