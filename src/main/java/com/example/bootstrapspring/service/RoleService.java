package com.example.bootstrapspring.service;


import com.example.bootstrapspring.entity.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAll();


    Role getByRoleName(String roleName);

    void addRole(Role role);
}
