package com.example.demo.mapper;

import com.example.demo.dto.StatusDTO;
import com.example.demo.repos.StatusRepo;
import com.example.demo.repos.TaskRepo;
import com.example.demo.dto.MonitorDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.MonitorEntity;
import com.example.demo.entity.StatusEntity;
import com.example.demo.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public abstract class MyMapper {
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    TaskRepo taskRepo;

    abstract public StatusEntity statusDtoToEntity(StatusDTO statusDTO);
    abstract public StatusDTO statusEntityToDto(StatusEntity statusEntity);


    @Mapping(source = "statusId", target = "status",qualifiedByName = "statusIdToEntity")
    abstract public TaskEntity  taskToEntity(TaskDTO taskDTO);


    @Mapping(source = "status", target = "statusId",qualifiedByName = "statusEntityToId")
    abstract public TaskDTO taskToDTO(TaskEntity taskEntity);

    @Mapping(source = "task", target = "taskId",qualifiedByName = "taskEntityToId")
    @Mapping(source = "status", target = "statusId",qualifiedByName = "statusEntityToId")
    abstract public MonitorDTO monitorToDTO(MonitorEntity monitorEntity);

    @Mapping(source = "taskId", target = "task",qualifiedByName = "taskIdToEntity")
    @Mapping(source = "statusId", target = "status",qualifiedByName = "statusIdToEntity")
    abstract public MonitorEntity monitorToEntity(MonitorDTO monitorDTO);

    abstract public List<MonitorDTO> monitorEntityListToDTO(List<MonitorEntity> entities);

    @Named("statusEntityToId")
    protected long statusEntityToId(StatusEntity status) {
        return status.getId();
    }

    @Named("taskIdToEntity")
    protected TaskEntity taskEntityToId(long taskId) {
        return taskRepo.findById(taskId).get();
    }

    @Named("taskEntityToId")
    protected long taskIdToEntity(TaskEntity task) {
        return task.getId();
    }

    @Named("statusIdToEntity")
    protected StatusEntity statusIdToEntity(long statusId) {
        return statusRepo.findById(statusId).get();
    }
}

