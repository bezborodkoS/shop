package com.example.shop.converter;

import com.example.shop.model.dto.CategoryDto;
import com.example.shop.model.enity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {
    private final ProductConverter productConverter;

    public CategoryConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public CategoryDto toDTO(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setName(category.getName());
//        TODO may be more beautiful
        dto.setProducts(productConverter.toDTOList(category.getProducts()));
        return dto;
    }

    public Category toEntity(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public List<CategoryDto> toDTOList(List<Category> categoryList) {
        return categoryList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Category> toEntityList(List<CategoryDto> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
