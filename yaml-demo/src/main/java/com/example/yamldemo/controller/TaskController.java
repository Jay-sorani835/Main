package com.example.yamldemo.controller;

import com.example.yamldemo.dto.TaskCreateRequest;
import com.example.yamldemo.dto.TaskResponse;
import com.example.yamldemo.model.enums.TaskStatus;
import com.example.yamldemo.service.TaskService;
import com.example.yamldemo.service.impl.ExcuseGeneratorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

	@Autowired
    private TaskService taskService;
    @Autowired
	private ExcuseGeneratorService excuseGeneratorService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskCreateRequest request) {

    	return new ResponseEntity<>(taskService.createTask(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(
            @RequestParam(name = "status", required = false) TaskStatus status) {
        if (status != null) {
            return ResponseEntity.ok(taskService.getTasksByStatus(status));
        }
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable(name = "id") int id,
            @Valid @RequestBody TaskCreateRequest request) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") int id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/excuse")
    public ResponseEntity<String> getExcuse() {
        return ResponseEntity.ok(excuseGeneratorService.generateExcuse());
    }
}
