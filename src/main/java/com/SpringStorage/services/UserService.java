package com.SpringStorage.services;

import com.SpringStorage.entities.Clothes;
import com.SpringStorage.entities.Role;
import com.SpringStorage.entities.User;
import com.SpringStorage.repositories.RoleRepository;
import com.SpringStorage.repositories.UserRepository;
import com.SpringStorage.specifications.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User saveUser(User user){
        log.info("Saving user login = {}", user.getLogin());
        return userRepository.save(user);
    }

    public Role saveRole(Role role){
        log.info("Saving new role name = {}", role.getName());
        return roleRepository.save(role);
    }

    public User addRoleToUser(String login, String roleName){
        User user = userRepository.findUserByLogin(login);
        Role role = roleRepository.findRoleByName(roleName);

        user.getRoles().add(role);
        return user;
    }

    public Page<User> findAll(Specification<User> specification, int page){
        return userRepository.findAll(specification, PageRequest.of(page, 3));
    }

    public Specification<User> createSpecificationBy(String login, String username, String role, String createdAt){
        Specification<User> specification = Specification.where(null);

        if (login != null) {
            specification = specification.and(UserSpecification.loginLike(login));
        }
        if (username != null) {
            specification = specification.and(UserSpecification.usernameLike(username));
        }
        if (createdAt != null) {
            specification = specification.and(UserSpecification.createdAt(createdAt));
        }
        return specification;
    }
}
