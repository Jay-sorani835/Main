package com.example.yamldemo.service;

import com.example.yamldemo.dto.GamificationResponse;
import com.example.yamldemo.dto.TaskCreateRequest;
import com.example.yamldemo.dto.TaskResponse;
import com.example.yamldemo.model.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponse createTask(TaskCreateRequest request, String username);

    List<TaskResponse> getAllTasksForUser(String username);

    TaskResponse getTaskById(int taskId, String username);

    TaskResponse updateTask(int taskId, TaskCreateRequest request, String username);

    GamificationResponse completeTask(int taskId, String username);

    void deleteTask(int taskId, String username);

    List<TaskResponse> getTasksByStatus(TaskStatus status, String username);
}
