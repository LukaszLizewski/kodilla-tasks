package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"title", "content");
        //When
        Task resultTask = taskMapper.mapToTask(taskDto);
        String resultGetTitle = resultTask.getTitle();
        String resultGetContent = resultTask.getContent();
        //Then
        assertNotNull(resultTask);
        assertEquals("title",resultGetTitle);
        assertEquals("content",resultGetContent);

    }
    @Test
    public void shouldMapToTaskDto() {
        //Given
        Task task = new Task(1L,"title", "content");
        //When
        TaskDto resultTaskDto = taskMapper.mapToTaskDto(task);
        String resultGetTitle = resultTaskDto.getTitle();
        String resultGetContent = resultTaskDto.getContent();

        assertNotNull(resultTaskDto);
        assertEquals("title",resultGetTitle);
        assertEquals("content",resultGetContent);
    }
    @Test
    public void shouldMapToTaskDtoList() {
        //Given
        Task task = new Task(1L,"title", "content");
        List<Task> list = new ArrayList<>();
        list.add(task);
        //When
        List<TaskDto> resultList = taskMapper.mapToTaskDtoList(list);
        int resultListSize = resultList.size();
        String resultGetTitle = resultList.get(0).getTitle();
        String resultGetContent = resultList.get(0).getContent();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultListSize);
        assertEquals("title", resultGetTitle);
        assertEquals("content", resultGetContent);
    }
}