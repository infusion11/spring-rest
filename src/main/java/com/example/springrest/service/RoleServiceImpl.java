package com.example.springrest.service;

import com.example.springrest.model.Role;
import com.example.springrest.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role insertRole(Role role) {
        return roleRepository.save(role);
    }
}
