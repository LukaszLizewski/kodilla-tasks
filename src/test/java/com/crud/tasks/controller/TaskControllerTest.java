package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskController taskController;
    @Test
    public void shouldFetchTasks () throws Exception {
        //Given
        List<TaskDto> listOfTasks = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto(123L, "test_title1", "test_content1");
        TaskDto taskDto2 = new TaskDto(124L, "test_title2", "test_content2");
        listOfTasks.add(taskDto1);
        listOfTasks.add(taskDto2);
        when(taskController.getTasks()).thenReturn(listOfTasks);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                //Task fields
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(123)))
                .andExpect(jsonPath("$[1].id",is(124)))
                .andExpect(jsonPath("$[0].title",is("test_title1")))
                .andExpect(jsonPath("$[1].title",is("test_title2")))
                .andExpect(jsonPath("$[0].content",is("test_content1")))
                .andExpect(jsonPath("$[1].content",is("test_content2")));
    }
    @Test
    public void shouldFetchTask () throws Exception {
        //Given
        List<TaskDto> listOfTasks = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto(123L, "test_title1", "test_content1");
        when(taskController.getTask(123L)).thenReturn(taskDto1);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask")
                .param("taskId","123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                //Task fields
                .andExpect(jsonPath("$.id",is(123)))
                .andExpect(jsonPath("$.title",is("test_title1")))
                .andExpect(jsonPath("$.content",is("test_content1")));
    }
    @Test
    public void shouldDeleteTask () throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .param("taskId","123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

    }
    @Test
    public void shouldCreateAndGetTask () throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(123L, "test_title1", "test_content1");
        when(taskController.getTask(123L)).thenReturn(taskDto1);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto1);
        //When & Then
                //Create
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
                 //Get
        mockMvc.perform(get("/v1/task/getTask")
                .param("taskId","123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                //Task fields
                .andExpect(jsonPath("$.id",is(123)))
                .andExpect(jsonPath("$.title",is("test_title1")))
                .andExpect(jsonPath("$.content",is("test_content1")));
    }
    @Test
    public void shouldUpdateTask () throws Exception {
        //Given
        TaskDto taskDto2 = new TaskDto(124L, "test_title2", "test_content2");
        when(taskController.updateTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(taskDto2);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto2);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
        //Task fields
                .andExpect(jsonPath("$.id",is(124)))
                .andExpect(jsonPath("$.title",is("test_title2")))
                .andExpect(jsonPath("$.content",is("test_content2")));

    }
}