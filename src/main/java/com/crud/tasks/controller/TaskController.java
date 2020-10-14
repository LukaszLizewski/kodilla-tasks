package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks () {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }
    public List<TaskDto> getTask () {
        return new ArrayList<>();
    }
    @RequestMapping(method = RequestMethod.GET, value = "getTask({id})")
    public TaskDto getTask (@PathVariable("id") Long taskId) {
        Optional<Task> result;
        result = service.getTask(taskId);
        return taskMapper.mapToTaskDto(result.get());
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask (Long taskId) {
    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask (TaskDto taskDto) {
        return new TaskDto(1L,"edited test title" , "test content");
    }
    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask (TaskDto taskDto) {
    }
}
