package com.eventmanagement.repository;

import com.eventmanagement.entity.Event;
//import com.eventmanagement.payload.AllEventDto;
import com.eventmanagement.payload.EventDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    // @Query("SELECT new com.eventmanagement.payload.AllEventDto(e.eventId, e.eventName, e.location, e.user.id) FROM Event e")
    // List<AllEventDto> findAllEvents();


    @Query("SELECT e FROM Event e WHERE DATE(e.dateTime) BETWEEN :startDate AND :endDate AND e.duration BETWEEN :minDuration AND :maxDuration")
    List<Event> findByDateTimeAndDurationRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("minDuration") int minDuration,
            @Param("maxDuration") int maxDuration);


    List<Event> findByOrganizerAndNumberOfAttendees(String organizer, int numberOfAttendees);


    @Query("SELECT " +

            "u.userFirstName, " +
            "u.userLastName, " +
            "DATE(e.dateTime) " +
            "SUM(e.numberOfAttendees) as dailyAttendeesCount, " +
            "FROM " +
            "Event e " +
            "JOIN " +
            "e.user u " +
            "GROUP BY " +
            "u.userFirstName, " +
            "u.userLastName, " +
            "DATE(e.dateTime)")
    List<Object[]> getDailyAttendeesCount();


}
