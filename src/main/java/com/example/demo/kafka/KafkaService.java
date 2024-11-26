package com.example.demo.kafka;

import com.example.demo.repos.TaskRepo;
import com.example.demo.dto.TaskDTO;
import com.example.demo.mapper.MyMapper;
import com.example.demo.service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class KafkaService  {
    static int prodNameId=1;

    private final KafkaTemplate<Long, TaskDTO> kafkaTaskTemplate;
    private final ObjectMapper objectMapper;
    @Autowired
    TaskRepo taskRepo;
    @Autowired
    MyMapper mapper;
    @Autowired
    TaskService taskService;





    @Autowired
    public KafkaService(KafkaTemplate<Long, TaskDTO> kafkaTaskTemplate,
                               ObjectMapper objectMapper) {
        this.kafkaTaskTemplate = kafkaTaskTemplate;
        this.objectMapper = objectMapper;
    }


    public void send(TaskDTO dto) {
        kafkaTaskTemplate.send("server.task", dto);
    }

    @KafkaListener(id = "Task", topics = {"server.task"}, containerFactory = "singleFactory")
    public void consume(TaskDTO taskDTO) {
        log.info("=> consumed {}", writeValueAsString(taskDTO));
        taskService.addTask(taskDTO);

    }
    @Transactional
    @Scheduled( fixedDelay = 6000)
    public void produce() {
        TaskDTO dto = new TaskDTO(String.format("name%s",prodNameId),10000);
        log.info("<= sending {}", writeValueAsString(dto));
        send(dto);
        prodNameId++;
    }

    private String writeValueAsString(TaskDTO dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}