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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user){
        log.info("Saving user username = {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User saveUser(String username, String email, String password){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(List.of(new Role("ROLE_USER")));
        userRepository.save(user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Role saveRole(Role role){
        log.info("Saving new role name = {}", role.getName());
        return roleRepository.save(role);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public void addRoleToUser(String login, String roleName){
        User user = userRepository.findUserByUsername(login).orElseThrow(() -> new RuntimeException("User not found username=" + login));
        Role role = roleRepository.findRoleByName(roleName);

        user.getRoles().add(role);
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(s).orElseThrow(() -> new RuntimeException("User not found login=" + s));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
