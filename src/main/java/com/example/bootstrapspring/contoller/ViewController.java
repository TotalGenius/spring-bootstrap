package com.example.bootstrapspring.contoller;

import com.example.bootstrapspring.service.RoleService;
import com.example.bootstrapspring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ViewController {

    private final UserService userService;
    private final RoleService roleService;

    public ViewController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String auth() {
        return "index";
    }

    @GetMapping("/api/user")
    public String userPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getByUserName(principal.getName()).get());
        return "user-page";
    }

    @GetMapping("/api/admin")
    public String adminPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getByUserName(principal.getName()).get());
        model.addAttribute("AllUsers", userService.getAll());
        model.addAttribute("roleUser", roleService.getAll());
        return "admin-page";
    }
}
