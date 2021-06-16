package br.com.adirvic.task.services;

import br.com.adirvic.task.model.ERole;
import br.com.adirvic.task.model.Role;
import br.com.adirvic.task.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(ERole role){
        return roleRepository.findByName(role);
    }
}