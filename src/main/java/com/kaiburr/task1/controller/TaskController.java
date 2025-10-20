package com.kaiburr.task1.controller;

import com.kaiburr.task1.dto.ExecutionRequest;
import com.kaiburr.task1.model.Task;
import com.kaiburr.task1.model.TaskExecution;
import com.kaiburr.task1.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;
    public TaskController(TaskService service) { this.service = service; }
    @GetMapping
    public ResponseEntity<?> getTasks(@RequestParam(value = "id", required = false) String id) {
        if (id != null && !id.isBlank()) return ResponseEntity.ok(service.getTaskById(id));
        return ResponseEntity.ok(service.getAllTasks());
    }
    @PutMapping
    public ResponseEntity<Task> createOrUpdate(@Valid @RequestBody Task task) {
        return ResponseEntity.ok(service.createOrUpdateTask(task));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable String id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/find")
    public ResponseEntity<?> findByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(service.findByName(name));
    }
    @PutMapping("/{id}/execution")
    public ResponseEntity<TaskExecution> runExecution(@PathVariable("id") String id, @RequestBody(required = false) ExecutionRequest req) throws Exception {
        return ResponseEntity.ok(service.runTask(id, req));
    }
}
