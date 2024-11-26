package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "task", schema = "public", catalog = "nexignTestCase")
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class TaskEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long id=null;
    @Size(min = 1,max = 50)
    @Column(name="name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    private StatusEntity status;
    @Min(50)
    @Max(7_889_238_000L)
    @Column(name = "duration")
    private long duration;


}
