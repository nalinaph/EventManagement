package com.eventmanagement.repository;

import com.eventmanagement.entity.Event;
import com.eventmanagement.payload.AllEventDto;
import com.eventmanagement.payload.EventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT new com.eventmanagement.payload.AllEventDto(e.eventId, e.eventName, e.location, e.user.id) FROM Event e")

    List<AllEventDto> findAllEvents();

}
