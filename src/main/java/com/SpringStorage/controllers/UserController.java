package com.SpringStorage.controllers;

import com.SpringStorage.entities.Role;
import com.SpringStorage.entities.User;
import com.SpringStorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "login", required = false) String login,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "has_role", required = false) String hasRole,
            @RequestParam(name = "created_at", required = false) String created_at){
        if (page < 1){
            page = 1;
        }

        Specification<User> specification = userService.createSpecificationBy(login, username, hasRole, created_at);
        return ResponseEntity.ok().body(userService.findAll(specification, page - 1).getContent());
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveNewUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/store/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/addRole")
    public ResponseEntity<User> addRoleToUser(@RequestBody User user, @RequestBody Role role){
        userService.addRoleToUser(user.getUsername(), role.getName());
        return ResponseEntity.ok().body(user);
    }
}
