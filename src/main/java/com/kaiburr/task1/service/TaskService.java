package com.kaiburr.task1.service;

import com.kaiburr.task1.dto.ExecutionRequest;
import com.kaiburr.task1.exception.ResourceNotFoundException;
import com.kaiburr.task1.model.Task;
import com.kaiburr.task1.model.TaskExecution;
import com.kaiburr.task1.repository.TaskRepository;
import com.kaiburr.task1.util.CommandValidator;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository repo;
    public TaskService(TaskRepository repo) { this.repo = repo; }
    public List<Task> getAllTasks() { return repo.findAll(); }
    public Task getTaskById(String id) {
        Optional<Task> opt = repo.findById(id);
        return opt.orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
    }
    public Task createOrUpdateTask(Task task) {
        if (!CommandValidator.isSafe(task.getCommand())) throw new IllegalArgumentException("Command unsafe.");
        return repo.save(task);
    }
    public void deleteTask(String id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Task not found: " + id);
        repo.deleteById(id);
    }
    public List<Task> findByName(String name) {
        List<Task> found = repo.findByNameContainingIgnoreCase(name);
        if (found.isEmpty()) throw new ResourceNotFoundException("No tasks found: " + name);
        return found;
    }
    public TaskExecution runTask(String taskId, ExecutionRequest req) throws Exception {
        Task t = getTaskById(taskId);
        String cmd = (req != null && req.getCommand() != null && !req.getCommand().isBlank()) ? req.getCommand() : t.getCommand();
        if (!CommandValidator.isSafe(cmd)) throw new IllegalArgumentException("Command unsafe.");
        Instant start = Instant.now();

        // Choose the right shell on Windows vs Unix-like systems
        String os = System.getProperty("os.name").toLowerCase();
        String[] command;
        if (os.contains("win")) {
            command = new String[] {"cmd.exe", "/c", cmd};
        } else {
            command = new String[] {"sh", "-c", cmd};
        }
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        String output;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            output = br.lines().collect(Collectors.joining("\n"));
        }
        int exit = p.waitFor();
        Instant end = Instant.now();
        TaskExecution exec = new TaskExecution(start, end, "[exit=" + exit + "]\n" + output);
        t.addExecution(exec);
        repo.save(t);
        return exec;
    }
}
