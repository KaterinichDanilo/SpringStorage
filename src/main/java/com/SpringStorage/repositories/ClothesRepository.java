package com.SpringStorage.repositories;

import com.SpringStorage.entities.Clothes;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long>, JpaSpecificationExecutor<Clothes> {
    Optional<Clothes> findClothesById(Long id);
    Optional<Clothes> findClothesByTitle(String title);
    Optional<Clothes> findClothesBySize(String size);
    Optional<Clothes> findClothesByPriceBetween(double min, double max);
}
