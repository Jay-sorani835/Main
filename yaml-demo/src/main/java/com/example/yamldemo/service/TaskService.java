package com.example.yamldemo.service;

import com.example.yamldemo.dto.TaskCreateRequest;
import com.example.yamldemo.dto.TaskResponse;
import com.example.yamldemo.model.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponse createTask(TaskCreateRequest request);

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(int taskId);

    TaskResponse updateTask(int taskId, TaskCreateRequest request);

    void deleteTask(int taskId);

    List<TaskResponse> getTasksByStatus(TaskStatus status);
}
