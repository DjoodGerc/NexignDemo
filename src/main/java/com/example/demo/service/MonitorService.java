package com.example.demo.service;

import com.example.demo.dto.MonitorDTO;
import com.example.demo.entity.MonitorEntity;
import com.example.demo.entity.TaskEntity;
import com.example.demo.mapper.MyMapper;
import com.example.demo.repos.MonitorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MonitorService {
    @Autowired
    MonitorRepo monitorRepo;
    @Autowired
    MyMapper mapper;
    public void saveToMonitor(TaskEntity entity){
        MonitorEntity monitorEntity=new MonitorEntity(entity, LocalDateTime.now(),entity.getStatus());
        monitorRepo.saveAndFlush(monitorEntity);
    }

    public List<MonitorDTO> getAllMonitor(){
        return mapper.monitorEntityListToDTO(monitorRepo.findAll());
    }
    public List<MonitorDTO> getFirstNMonitor(int n){
        return mapper.monitorEntityListToDTO(monitorRepo.findBottomNByOrderByIdAsc(n));
    }
}
