package com.rinchik.practise.controller;

import com.rinchik.practise.model.Event;
import com.rinchik.practise.model.User;
import com.rinchik.practise.repository.EventRepository;
import com.rinchik.practise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventRepository eventRepo;
    private final UserRepository userRepo;

    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    @GetMapping
    public String listEvents(Model model, Principal principal) {
        log.info("Запрос списка мероприятий от пользователя: {}", principal.getName());
        User organizer = userRepo.findByUsername(principal.getName()).orElseThrow();
        model.addAttribute("events", eventRepo.findByOrganizer(organizer));
        return "events/list";
    }

    @PostMapping
    public String createEvent(@ModelAttribute Event event, Principal principal) {
        User organizer = userRepo.findByUsername(principal.getName()).orElseThrow();
        event.setOrganizer(organizer);
        eventRepo.save(event);
        return "redirect:/events";
    }
}
