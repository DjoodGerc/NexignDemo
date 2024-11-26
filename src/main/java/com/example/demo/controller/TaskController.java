package com.example.demo.controller;

import com.example.demo.dto.MonitorDTO;
import com.example.demo.dto.StatusDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.TaskEntity;
import com.example.demo.mapper.MyMapper;
import com.example.demo.repos.TaskRepo;
import com.example.demo.service.MonitorService;
import com.example.demo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class TaskController {

    @Autowired
    TaskRepo taskRepo;
    @Autowired
    MyMapper mapper;
    @Autowired
    TaskService taskService;
    @Autowired
    MonitorService monitorService;

    @Operation(summary = "Get task by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the task",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content) })
    @GetMapping(value = "/getTaskById/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable(name = "id") long id){
        TaskEntity taskEntity=taskRepo.findById(id).orElseThrow();
        TaskDTO taskDTO=mapper.taskToDTO(taskEntity);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get task by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the task",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid name supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content) })
    @GetMapping(value = "/getTaskByName/{name}")
    public ResponseEntity<TaskDTO> getTaskByName(@PathVariable(name = "name") String name){
        TaskEntity taskEntity=taskRepo.findByName(name).orElseThrow();

        TaskDTO taskDTO=mapper.taskToDTO(taskEntity);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }
    @Operation(summary = "Get status by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the status",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatusDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Status not found",
                    content = @Content) })
    @GetMapping(value = "/getTaskStatusById/{id}")
    public ResponseEntity<StatusDTO> getTaskStatusById(@PathVariable(name="id") Long id){
        TaskEntity taskEntity=taskRepo.findById(id).orElseThrow();

        StatusDTO statusDTO=mapper.statusEntityToDto(taskEntity.getStatus());
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }


    @Operation(summary = "Save task to monitor by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the task and saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content) })
    @PostMapping(value = "/saveTaskToMonitor/{id}")
    public ResponseEntity<TaskDTO> saveTaskToMonitor(@PathVariable(name="id") Long id){
        TaskEntity taskEntity=taskRepo.findById(id).orElseThrow();
        monitorService.saveToMonitor(taskEntity);
        return new ResponseEntity<>(mapper.taskToDTO(taskEntity), HttpStatus.OK);
    }

    @Operation(summary = "Get all monitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the status",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MonitorDTO.class)) })
    })
    @GetMapping(value = "/getAllMonitor")
    public ResponseEntity<List<MonitorDTO>> getAllMonitor(){
        List<MonitorDTO> monitorDTOS=monitorService.getAllMonitor();
        return new ResponseEntity<>(monitorDTOS, HttpStatus.OK);
    }
    @Operation(summary = "Get last n rows from Monitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the last n rows",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MonitorDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid n supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Monitor Entities not found",
                    content = @Content) })
    @GetMapping(value = "/getLastNMonitor/{n}")
    public ResponseEntity<List<MonitorDTO>> getLastNMonitor(@PathVariable(name = "n") int n){
        List<MonitorDTO> monitorDTOS=monitorService.getFirstNMonitor(n);
        return new ResponseEntity<>(monitorDTOS, HttpStatus.OK);
    }

    


}
