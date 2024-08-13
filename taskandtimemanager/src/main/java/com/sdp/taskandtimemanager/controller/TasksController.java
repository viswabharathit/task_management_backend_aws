package com.sdp.taskandtimemanager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdp.taskandtimemanager.dto.TaskFormDto;
import com.sdp.taskandtimemanager.model.Tasks;
import com.sdp.taskandtimemanager.service.TasksService;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TasksController {
    @Autowired
    private TasksService service;

    @GetMapping("/findAll")
    public List<Tasks> findAll() {
        return service.findAllTasks();
    }

    @GetMapping("/findById/{taskId}")
    public Tasks findById(@PathVariable Long taskId) {
        return service.findTaskById(taskId);
    }

    @GetMapping("/user/{userId}")
    public List<Tasks> getTasksByUserId(@PathVariable Long userId) {
        return service.findTasksByUserId(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody TaskFormDto task) {

        Long projectId = task.getProjectid();
        Long userId = task.getAssignedto();
        // System.out.println("``````````` Project ID: " + task.toString());
        // System.out.println(projectId + "``````````" + userId);

        String result = service.addTask(task, projectId, userId);

        if ("Project not found".equals(result) || "User not found".equals(result)) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok("Task added successfully");
    }

    @PutMapping("/update/{taskId}")
    public String update(@PathVariable Long taskId, @RequestBody TaskFormDto task) {
        Long projectId = task.getProjectid();
        Long userId = task.getAssignedto();
        return service.updateTask(taskId, task, projectId, userId);
    }

    @PatchMapping("/updateSpecific/{taskId}")
    public String patch(@PathVariable Long taskId, @RequestBody TaskFormDto task) {
        Long projectId = task.getProjectid();
        Long userId = task.getAssignedto();
        return service.patchTask(taskId, task, projectId, userId);
    }

    @PatchMapping("/updateUsertask/{taskId}")
    public String patchUserTask(@PathVariable Long taskId, @RequestBody TaskFormDto task) {
        // Long projectId = task.getProjectid();
        // Long userId = task.getAssignedto();
        return service.patchUserTask(taskId, task);
    }

    @DeleteMapping("/delete/{taskId}")
    public void delete(@PathVariable Long taskId) {
        service.deleteTask(taskId);
    }

}