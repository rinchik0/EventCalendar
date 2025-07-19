package com.rinchik.practise.controller;

import com.rinchik.practise.model.User;
import com.rinchik.practise.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepo;

    @GetMapping("/list")
    public String listTasks(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "users/list";
    }

    @PostMapping("/change_role")
    public String changeUserRole(@RequestParam Long userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        user.setRole("ADMIN");
        userRepo.save(user);
        return "redirect:/users/list";
    }
}
