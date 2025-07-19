package com.rinchik.practise.controller;

import com.rinchik.practise.model.Event;
import com.rinchik.practise.model.Task;
import com.rinchik.practise.model.TaskStatus;
import com.rinchik.practise.model.User;
import com.rinchik.practise.repository.EventRepository;
import com.rinchik.practise.repository.TaskRepository;
import com.rinchik.practise.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskRepository taskRepo;
    private final UserRepository userRepo;
    private final EventRepository eventRepo;

    @GetMapping("/list")
    public String listTasks(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("tasks", taskRepo.findByAssignee(user));
        return "tasks/list";
    }

    @GetMapping("/new")
    public String showTaskForm(
            @RequestParam Long eventId,
            Model model) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        Task task = new Task();
        task.setEvent(event);

        model.addAttribute("task", task);
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("event", event);
        return "tasks/create";
    }

    @PostMapping("/create")
    public String createTask(
            @RequestParam String title,
            @ModelAttribute("task") Task task,
            @RequestParam Long assigneeId,
            Principal principal) {

        User assignee = userRepo.findById(assigneeId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        task.setTitle(title);
        task.setAssignee(assignee);
        task.setStatus(TaskStatus.NEW);

        taskRepo.save(task);

        return "redirect:/events/list";
    }

    @PostMapping("/tasks/status/change")
    public String changeTaskStatus(@RequestParam Long taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException("Task not found")
        );
        task.changeStatus();
        taskRepo.save(task);
        return "redirect:/tasks/list";
    }
}
