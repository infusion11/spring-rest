package com.example.springrest;

import com.example.springrest.model.Role;
import com.example.springrest.model.User;
import com.example.springrest.repository.RoleRepository;
import com.example.springrest.repository.UserRepository;
import com.example.springrest.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock private UserServiceImpl userService;

    @Mock private UserRepository userRepository;
    @Mock private RoleRepository roleRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @BeforeEach
    void init() {
        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);
    }

    @Test
    public void addUserTest() {
        User newUser = new User(1, "testuser", "testpassword", new ArrayList<>());

        when(userRepository.save(Mockito.any())).thenReturn(newUser);

        User user = userService.addUser(newUser);

        Mockito.verify(userRepository).save(Mockito.any());
        System.out.println("Saved username is: " + user.getUsername());
    }
    @Test
    public void getUserTest() {
        User newUser = new User(1, "testuser", "testpassword", new ArrayList<>());

        when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(newUser));

        Optional<User> user = userService.getUser("testuser");

        Assertions.assertEquals(user.get().getUsername(), newUser.getUsername());
        System.out.println(user.get().getUsername() +" equals "+ newUser.getUsername());
    }
    @Test
    public void addRoleToUserTest() {
        User user = new User(1, "testuser", "testpassword", new ArrayList<>());
        Role role = new Role(1, "ROLE_VIP");
        when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(user));
        when(roleRepository.findByName(Mockito.any())).thenReturn(role);
        Role roleToAdd = roleRepository.findByName(role.getName());
        Collection<Role> newRole = new ArrayList<>();
        newRole.add(roleToAdd);
        Optional<User> getUser = userRepository.findByUsername(user.getUsername());
        getUser.get().setRole(newRole);
        Assertions.assertNotNull(getUser.get().getRole());
        System.out.println(getUser);
    }
}
