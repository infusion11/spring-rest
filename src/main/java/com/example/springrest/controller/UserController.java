package com.example.springrest.controller;

import com.example.springrest.helperclass.UserAndRole;
import com.example.springrest.model.Role;
import com.example.springrest.model.User;
import com.example.springrest.service.RoleService;
import com.example.springrest.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/restapi/v1/user")
public class UserController {
    private final UserServiceImpl userService;
    private final RoleService roleService;

    public UserController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    //ADMIN only
    @PostMapping("/addrole")
    public ResponseEntity<Role> addRole(@Valid @RequestBody Role role) {
        roleService.insertRole(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }
    @PostMapping("/adduser")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    //ADMIN only
    @GetMapping("/getuser/{username}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("username") String username){
        System.out.println(username);
        Optional<User> user = userService.getUser(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    //ADMIN only
    @PostMapping("/giveroletouser")
    public ResponseEntity<?> giveRole(@RequestBody UserAndRole information) {
        userService.giveRoleToUser(information.getUser(), information.getRoleName());
        return new ResponseEntity<>(information, HttpStatus.CREATED);
    }
}
