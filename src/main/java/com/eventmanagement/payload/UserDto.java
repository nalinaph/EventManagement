package com.eventmanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    // private long id;
    private String userFirstname;
    private String userLastname;
}
