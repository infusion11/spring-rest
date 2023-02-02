package com.example.springrest.service;

import com.example.springrest.model.Role;
import com.example.springrest.model.User;
import com.example.springrest.repository.RoleRepository;
import com.example.springrest.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addUser(User user) {
        Optional<User> userToAdd = userRepository.findByUsername(user.getUsername());
        if(userToAdd.isPresent()) {
            throw new RuntimeException("User already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Collection<Role> role = new ArrayList<>();
        role.add(roleRepository.findByName("ROLE_USER").get());
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()) {
            throw new RuntimeException("User does not exists.");
        }
        return optionalUser;
    }

    @Override
    public void giveRoleToUser(String username, String role) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Role roleToAdd = roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Role does not exists."));
        System.out.println(roleToAdd);
        if(!optionalUser.isPresent()) {
            throw new RuntimeException("User does not exists.");
        }
        if(optionalUser.get().getRole().contains(roleToAdd)) {
            throw new RuntimeException("This role is already assigned.");
        }
        Collection<Role> newRole = new ArrayList<>();
        newRole.add(roleToAdd);
        optionalUser.get().setRole(newRole);
    }
}
