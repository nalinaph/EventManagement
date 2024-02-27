package com.eventmanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    //private long EventId;
    private String eventName;
    private String location;
    private String organizer;
   private String numberOfAttendees;
   private LocalDateTime dateTime;
    private int duration;


}
