package com.example.yamldemo.service.impl;

import com.example.yamldemo.dto.TaskCreateRequest;
import com.example.yamldemo.dto.TaskResponse;
import com.example.yamldemo.exception.ResourceNotFoundException;
import com.example.yamldemo.model.Task;
import com.example.yamldemo.model.enums.TaskStatus;
import com.example.yamldemo.repository.TaskRepository;
import com.example.yamldemo.service.TaskService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	@Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional
    public TaskResponse createTask(TaskCreateRequest request) {

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPriority())
                .difficulty(request.getDifficulty())
                .xpReward(request.getDifficulty().getXpReward())
                .dueDate(request.getDueDate())
                .build();

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getTaskById(int taskId) {
        Task task = getTaskByIdOrThrow(taskId);
        return mapToResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(int taskId, TaskCreateRequest request) {
        Task task = getTaskByIdOrThrow(taskId);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());

        // Update difficulty and xp only if it changed
        if (task.getDifficulty() != request.getDifficulty()) {
            task.setDifficulty(request.getDifficulty());
            task.setXpReward(request.getDifficulty().getXpReward());
        }

        task.setDueDate(request.getDueDate());

        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    @Override
    @Transactional
    public void deleteTask(int taskId) {
        Task task = getTaskByIdOrThrow(taskId);
        taskRepository.delete(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private Task getTaskByIdOrThrow(int taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));
    }

    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .difficulty(task.getDifficulty())
                .xpReward(task.getXpReward())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}
