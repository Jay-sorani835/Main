package com.example.yamldemo.repository;

import com.example.yamldemo.model.Task;
import com.example.yamldemo.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserId(int userId);

    Optional<Task> findByIdAndUserId(int id, int userId);

    List<Task> findByUserIdAndStatus(int userId, TaskStatus status);
}
