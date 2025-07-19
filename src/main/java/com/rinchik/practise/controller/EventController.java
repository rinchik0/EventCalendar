package com.rinchik.practise.controller;

import com.rinchik.practise.model.Event;
import com.rinchik.practise.model.Task;
import com.rinchik.practise.model.User;
import com.rinchik.practise.repository.EventRepository;
import com.rinchik.practise.repository.TaskRepository;
import com.rinchik.practise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventRepository eventRepo;
    private final UserRepository userRepo;
    private final TaskRepository taskRepo;

    @GetMapping("/list")
    public String listEvents(Model model) {
        List<Event> events = eventRepo.findAll();

        Map<Long, List<Task>> tasksByEvent = new HashMap<>();
        for (Event event : events)
            tasksByEvent.put(event.getId(), taskRepo.findByEvent(event));

        model.addAttribute("events", events);
        model.addAttribute("tasksByEvent", tasksByEvent);
        return "events/list";
    }

    @GetMapping("/new")
    public String showEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/create";
    }

    @PostMapping("/create")
    public String createEvent(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy'T'HH:mm") LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy'T'HH:mm") LocalDateTime endTime,
            Principal principal) {

        User organizer = userRepo.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setOrganizer(organizer);

        eventRepo.save(event);

        return "redirect:/events/list";
    }
}
