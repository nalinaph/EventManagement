package com.eventmanagement.service.Impl;

import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.User;
import com.eventmanagement.payload.AllEventDto;
import com.eventmanagement.payload.EventDto;
import com.eventmanagement.payload.UserDto;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
        event = eventRepository.findById(eventId).get();
        return  mapToDto(event);

    }

    @Override
    public List<AllEventDto> findAllEvents()
    {
        return eventRepository.findAllEvents();
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
