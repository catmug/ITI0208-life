package ee.expensetracker.controller;

import ee.expensetracker.dao.CategoryDao;
import ee.expensetracker.dto.CategoryDto;
import ee.expensetracker.model.Category;
import ee.expensetracker.service.MyUserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        return dao.getCategoriesByUserId().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("category")
    public String save(@RequestBody CategoryDto category) throws ParseException {
        return dao.save(convertToModel(category));
    }

    @PostMapping("category/rename")
    public String rename(@RequestBody CategoryDto category) throws ParseException {
        return dao.rename(convertToModel(category));
    }

    private Category convertToModel(CategoryDto categoryDto) throws ParseException {
        return modelMapper.map(categoryDto, Category.class);
    }

    private CategoryDto convertToDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
