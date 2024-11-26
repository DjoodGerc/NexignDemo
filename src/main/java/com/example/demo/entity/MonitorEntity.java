package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "monitor")
@AllArgsConstructor
@NoArgsConstructor
public class MonitorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;

    @Column(name = "check_time", nullable = false)
    private LocalDateTime checkTime;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "status_id", nullable = false)
    private StatusEntity status;
    public MonitorEntity(TaskEntity task,LocalDateTime checkTime,StatusEntity status){
        this.task=task;
        this.checkTime=checkTime;
        this.status=status;
    }
    // Getters and Setters
}

