package com.SpringStorage.specifications;

import com.SpringStorage.entities.Clothes;
import org.springframework.data.jpa.domain.Specification;

public class ClothesSpecification {
    public static Specification<Clothes> priceGreaterOrEqualThan(Integer price){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Clothes> priceLessOrEqualThan(Integer price){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Clothes> titleLike(String title){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title)));
    }

    public static Specification<Clothes> sizeLike(String size){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("size"), String.format("%%%s%%", size)));
    }
}
