package com.example.demo.repos;

import com.example.demo.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends JpaRepository<StatusEntity,Long> {

}
