package com.example.demo.service;

import com.example.demo.dto.MonitorDTO;
import com.example.demo.entity.MonitorEntity;
import com.example.demo.repos.MonitorRepo;
import com.example.demo.repos.StatusRepo;
import com.example.demo.repos.TaskRepo;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.TaskEntity;
import com.example.demo.mapper.MyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
@Slf4j
public class TaskService {
    @Autowired
    ExecutorService executorService;
    @Autowired
    MyMapper mapper;
    @Autowired
    TaskRepo taskRepo;
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    MonitorService monitorService;

    public void addTask(TaskDTO taskDTO){
        TaskEntity entity=mapper.taskToEntity(taskDTO);
        entity=taskRepo.saveAndFlush(entity);
        monitorService.saveToMonitor(entity);
        taskDTO.setId(entity.getId());
        executorService.execute(new TaskRunnable(taskDTO,taskRepo,statusRepo,monitorService));

    }




}
