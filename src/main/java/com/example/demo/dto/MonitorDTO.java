package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorDTO {
    private long id;
    private long taskId;
    private LocalDateTime checkTime;
    private long statusId;
}
