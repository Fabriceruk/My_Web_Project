package com.example.question5_task_api.controller;

import com.example.question5_task_api.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1L, "Study Spring", "Learn REST APIs", false, "HIGH", "2026-02-15"));
        tasks.add(new Task(2L, "Do Assignment", "Complete all questions", false, "HIGH", "2026-02-20"));
        tasks.add(new Task(3L, "Shopping", "Buy groceries", true, "LOW", "2026-02-10"));
        tasks.add(new Task(4L, "Exercise", "Morning run", false, "MEDIUM", "2026-02-12"));
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return tasks.stream().filter(t -> t.getTaskId().equals(taskId)).findFirst().orElse(null);
    }

    @GetMapping("/status")
    public List<Task> getByStatus(@RequestParam boolean completed) {
        return tasks.stream().filter(t -> t.isCompleted() == completed).toList();
    }

    @GetMapping("/priority/{priority}")
    public List<Task> getByPriority(@PathVariable String priority) {
        return tasks.stream().filter(t -> t.getPriority().equalsIgnoreCase(priority)).toList();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {

        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setTitle(updatedTask.getTitle());
                t.setDescription(updatedTask.getDescription());
                t.setCompleted(updatedTask.isCompleted());
                t.setPriority(updatedTask.getPriority());
                t.setDueDate(updatedTask.getDueDate());
                return t;
            }
        }
        return null;
    }

    @PatchMapping("/{taskId}/complete")
    public Task markCompleted(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setCompleted(true);
                return t;
            }
        }
        return null;
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        tasks.removeIf(t -> t.getTaskId().equals(taskId));
    }
}
