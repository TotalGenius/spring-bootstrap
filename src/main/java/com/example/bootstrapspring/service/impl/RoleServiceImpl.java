package com.example.bootstrapspring.service.impl;


import com.example.bootstrapspring.entity.Role;
import com.example.bootstrapspring.repository.RoleRepository;
import com.example.bootstrapspring.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getAll() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleRepository.findRoleByRole(roleName).get();
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }
}
