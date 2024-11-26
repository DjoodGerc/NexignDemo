package com.example.demo.repos;

import com.example.demo.entity.MonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorRepo extends JpaRepository<MonitorEntity,Long> {
    @Query(value = "SELECT * FROM monitor e ORDER BY e.id DESC LIMIT :limit",nativeQuery = true)
    List<MonitorEntity> findBottomNByOrderByIdAsc(@Param("limit") int limit);
}
