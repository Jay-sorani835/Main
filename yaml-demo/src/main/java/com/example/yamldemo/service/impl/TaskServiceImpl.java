package com.example.yamldemo.service.impl;

import com.example.yamldemo.dto.GamificationResponse;
import com.example.yamldemo.dto.TaskCreateRequest;
import com.example.yamldemo.dto.TaskResponse;
import com.example.yamldemo.exception.ResourceNotFoundException;
import com.example.yamldemo.exception.UnauthorizedException;
import com.example.yamldemo.model.Task;
import com.example.yamldemo.model.User;
import com.example.yamldemo.model.enums.TaskStatus;
import com.example.yamldemo.repository.TaskRepository;
import com.example.yamldemo.repository.UserRepository;
import com.example.yamldemo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private GamificationService gamificationService;

    @Override
    @Transactional
    public TaskResponse createTask(TaskCreateRequest request, String username) {
        User user = getUserByUsername(username);

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPriority())
                .difficulty(request.getDifficulty())
                .xpReward(request.getDifficulty().getXpReward())
                .dueDate(request.getDueDate())
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasksForUser(String username) {
        User user = getUserByUsername(username);
        return taskRepository.findByUserId(user.getId()).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getTaskById(int taskId, String username) {
        Task task = getTaskAndVerifyOwnership(taskId, username);
        return mapToResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(int taskId, TaskCreateRequest request, String username) {
        Task task = getTaskAndVerifyOwnership(taskId, username);

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
    public GamificationResponse completeTask(int taskId, String username) {
        Task task = getTaskAndVerifyOwnership(taskId, username);

        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new IllegalArgumentException("Task is already completed!");
        }

        task.setStatus(TaskStatus.COMPLETED);
        taskRepository.save(task);

        return gamificationService.addXp(task.getUser(), task.getXpReward());
    }

    @Override
    @Transactional
    public void deleteTask(int taskId, String username) {
        Task task = getTaskAndVerifyOwnership(taskId, username);
        taskRepository.delete(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByStatus(TaskStatus status, String username) {
        User user = getUserByUsername(username);
        return taskRepository.findByUserIdAndStatus(user.getId(), status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Helper methods
    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
    }

    private Task getTaskAndVerifyOwnership(int taskId, String username) {
        User user = getUserByUsername(username);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

        if (task.getUser().getId() != user.getId()) {
            throw new UnauthorizedException("You do not have permission to access this task");
        }
        return task;
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
