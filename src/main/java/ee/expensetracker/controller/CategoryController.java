package ee.expensetracker.controller;

import ee.expensetracker.dao.CategoryDao;
import ee.expensetracker.dto.CategoryDto;
import ee.expensetracker.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {


    @Autowired
    private CategoryDao dao;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("category")
    public List<CategoryDto> getCategories() {
        return dao.getAllCategories().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("category")
    public void save(@RequestBody CategoryDto category) throws ParseException {
        dao.save(convertToModel(category));
    }

    @PostMapping("category/rename")
    public void rename(@RequestBody CategoryDto category) throws ParseException {
        dao.rename(convertToModel(category));
    }

    private Category convertToModel(CategoryDto categoryDto) throws ParseException {
        return modelMapper.map(categoryDto, Category.class);
    }

    private CategoryDto convertToDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
