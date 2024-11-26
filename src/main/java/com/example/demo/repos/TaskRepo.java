package com.example.demo.repos;

import com.example.demo.entity.TaskEntity;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity,Long> {
    Optional<TaskEntity> findByName(String name);
}
