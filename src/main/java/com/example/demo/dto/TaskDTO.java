package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO  {
    public Long id;
    @Size(min = 1,max = 50)
    private String name;
    private long statusId;
    @Min(50)
    @Max(7_889_238_000L) //3 months
    private Long duration;


    public TaskDTO(String name,long duration){
        this.name=name;
        this.duration=duration;
    }
}
