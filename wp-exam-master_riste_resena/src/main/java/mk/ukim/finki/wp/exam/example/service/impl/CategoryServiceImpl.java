package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.exam.example.repository.CategoryRepository;
import mk.ukim.finki.wp.exam.example.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

// isto za baranje 2.
@Service // dopisano , treba da anotirame @Service sekogas kaj servisnite uslugi
public class CategoryServiceImpl implements CategoryService {

    // dopisano
    private final CategoryRepository categoryRepository;

    // dopisano
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // dopisano
    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(InvalidCategoryIdException::new);
    }

    // dopisano
    @Override
    public List<Category> listAll() {
        return this.categoryRepository.findAll();
    }

    /// dopisano
    @Override
    public Category create(String name) {
        Category category = new Category(name);
        return this.categoryRepository.save(category);
        //
    } // dopisano implements CategoryService


}
