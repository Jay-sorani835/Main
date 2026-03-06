package com.example.yamldemo.controller;

import com.example.yamldemo.dto.GamificationResponse;
import com.example.yamldemo.dto.TaskCreateRequest;
import com.example.yamldemo.dto.TaskResponse;
import com.example.yamldemo.model.enums.TaskStatus;
import com.example.yamldemo.service.TaskService;
import com.example.yamldemo.service.impl.ExcuseGeneratorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private TaskService taskService;
    private ExcuseGeneratorService excuseGeneratorService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskCreateRequest request,
            Authentication authentication) {
        String username = authentication.getName();
        return new ResponseEntity<>(taskService.createTask(request, username), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(
            @RequestParam(required = false) TaskStatus status,
            Authentication authentication) {
        String username = authentication.getName();
        if (status != null) {
            return ResponseEntity.ok(taskService.getTasksByStatus(status, username));
        }
        return ResponseEntity.ok(taskService.getAllTasksForUser(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable int id, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(taskService.getTaskById(id, username));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable int id,
            @Valid @RequestBody TaskCreateRequest request,
            Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(taskService.updateTask(id, request, username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id, Authentication authentication) {
        String username = authentication.getName();
        taskService.deleteTask(id, username);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<GamificationResponse> completeTask(@PathVariable int id, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(taskService.completeTask(id, username));
    }

    @GetMapping("/excuse")
    public ResponseEntity<String> getExcuse() {
        return ResponseEntity.ok(excuseGeneratorService.generateExcuse());
    }
}
