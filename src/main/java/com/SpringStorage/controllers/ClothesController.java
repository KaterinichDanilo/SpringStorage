package com.SpringStorage.controllers;

import com.SpringStorage.entities.Clothes;
import com.SpringStorage.services.ClothesService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clothes")
@NoArgsConstructor
//@NoArgsConstructor
@Data
public class ClothesController {
    private ClothesService clothesService;

    @Autowired
    public ClothesController(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
    @GetMapping
    public List<Clothes> getAllClothes(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title", required = false) String titlePart,
            @RequestParam(name = "size", required = false) String size){

        if (page < 1){
            page = 1;
        }

        Specification<Clothes> specification = clothesService.createSpecificationBy(minPrice, maxPrice, titlePart, size);

        return clothesService.findAll(specification, page - 1).getContent();
    }
}
