package com.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;
    private String eventName;
    private String location;
    private String organizer;
    private int numberOfAttendees;
    private LocalDateTime dateTime;
    private int duration;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
