package com.SpringStorage.services;

import com.SpringStorage.entities.Clothes;
import com.SpringStorage.repositories.ClothesRepository;
import com.SpringStorage.specifications.ClothesSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Optional;

@Service
@NoArgsConstructor
@Data
public class ClothesService {
    ClothesRepository clothesRepository;

    @Autowired
    public ClothesService(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }

    @PostConstruct
    private void init(){
        System.out.println("init");
    }

    public Page<Clothes> findAll(Specification<Clothes> specification, int page){
        return clothesRepository.findAll(specification, PageRequest.of(page, 3));
    }

    public Optional<Clothes> findById(Long id){
        return clothesRepository.findClothesById(id);
    }

    public Optional<Clothes> findByTitle(String title){
        return clothesRepository.findClothesByTitle(title);
    }

    public Optional<Clothes> findBySize(String size){
        return clothesRepository.findClothesBySize(size);
    }

    public Specification<Clothes> createSpecificationBy(Integer minPrice, Integer maxPrice, String title, String size){
        Specification<Clothes> specification = Specification.where(null);

        if (minPrice != null) {
            specification = specification.and(ClothesSpecification.priceGreaterOrEqualThan(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ClothesSpecification.priceLessOrEqualThan(maxPrice));
        }
        if (title != null) {
            specification = specification.and(ClothesSpecification.titleLike(title));
        }
        if (size != null) {
            specification = specification.and(ClothesSpecification.sizeLike(size));
        }
        return specification;
    }
}
