package com.example.bootstrapspring.contoller;


import com.example.bootstrapspring.entity.Role;
import com.example.bootstrapspring.entity.User;
import com.example.bootstrapspring.service.RoleService;
import com.example.bootstrapspring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {
    private final UserService userService;
    private final RoleService roleService;

    public AppController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAll().stream().toList());
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> saveNewUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
