package com.example.demo.service;

import com.example.demo.repos.StatusRepo;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.TaskEntity;
import com.example.demo.repos.TaskRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
//@ComponentScan(basePackages = "com.example.demo.repos")
class TaskRunnable implements Runnable {
    protected TaskDTO taskDTO=null;
    TaskRepo taskRepo;
    StatusRepo statusRepo;
    MonitorService monitorService;
    public TaskRunnable(TaskDTO taskDTO,TaskRepo taskRepo,StatusRepo statusRepo,MonitorService monitorService){
        this.taskRepo=taskRepo;
        this.taskDTO=taskDTO;
        this.statusRepo=statusRepo;
        this.monitorService=monitorService;
    }
    public void run()
    {

        TaskEntity entity=taskRepo.findById(taskDTO.id).orElse(new TaskEntity());
        entity.setStatus(statusRepo.findById(1L).get());

        taskRepo.save(entity);
        monitorService.saveToMonitor(entity);
        log.info(String.format("task # %s in progress; duration: %s", taskDTO.getId().toString(),taskDTO.getDuration().toString()));

        try {
            Thread.sleep(taskDTO.getDuration());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        entity.setStatus(statusRepo.findById(2L).get());
        taskRepo.save(entity);
        monitorService.saveToMonitor(entity);
        log.info(String.format("task # %s finished", taskDTO.getId().toString()));


    }
}