package com.rinchik.practise.controller;

import com.rinchik.practise.model.User;
import com.rinchik.practise.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepo.findAll());
        return "register";
    }

    @PostMapping
    public String registerUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            @RequestParam String confirmPassword) {
        // Проверка на существующий username
        if (userRepo.existsByUsername(user.getUsername())) {
            bindingResult.rejectValue("username", "error.user",
                    "Этот логин уже занят");
        }

        // Проверка совпадения паролей
        if (!user.getPassword().equals(confirmPassword)) {
            bindingResult.rejectValue("password", "error.user",
                    "Пароли не совпадают");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Шифрование пароля и сохранение
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);

        return "redirect:/login?registered";
    }
}
