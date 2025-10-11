package com.github.marcoantoniolobo.usercrud.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {
    private Long id;
    private String name;
    private String eventType;
}
