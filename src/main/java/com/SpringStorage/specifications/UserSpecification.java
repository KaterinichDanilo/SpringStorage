package com.SpringStorage.specifications;

import com.SpringStorage.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> loginLike(String login){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("login"), String.format("%%%s%%", login)));
    }

    public static Specification<User> usernameLike(String username){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), String.format("%%%s%%", username)));
    }

    public static Specification<User> hasRole(String role){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("roles"), String.format("%%%s%%", role)));
    }

    public static Specification<User> createdAt(String createdAt){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdAt"), String.format("%%%s%%", createdAt)));
    }
}
