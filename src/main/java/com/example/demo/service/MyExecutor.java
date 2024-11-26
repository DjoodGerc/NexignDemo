package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MyExecutor {
    @Value("${worker.pool}")
    int pools;
    @Bean
    ExecutorService createThreadPool(){
        ExecutorService executorService= Executors.newFixedThreadPool(pools);
        return executorService;
    }


}
