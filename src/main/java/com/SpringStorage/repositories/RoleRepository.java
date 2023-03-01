package com.SpringStorage.repositories;

import com.SpringStorage.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
    Role findRoleByName(String name);
}
