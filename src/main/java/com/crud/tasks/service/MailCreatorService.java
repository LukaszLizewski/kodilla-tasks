package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_object", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
    public String buildSchedulerEmail(String message) {

        List<String> listOfTasks  = taskRepository.findAll().stream()
                .map(Task::getTitle)
                .collect(Collectors.toList());

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Show list of tasks");
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_object", adminConfig);
        context.setVariable("tasks_list", listOfTasks);
        return templateEngine.process("mail/scheduled-mail", context);
    }
}
