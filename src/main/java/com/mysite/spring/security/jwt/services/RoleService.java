package com.mysite.spring.security.jwt.services;

import com.mysite.spring.security.jwt.entities.Role;
import com.mysite.spring.security.jwt.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
