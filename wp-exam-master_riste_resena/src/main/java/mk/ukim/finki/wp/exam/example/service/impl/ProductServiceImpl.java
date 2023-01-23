package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.Product;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidProductIdException;
import mk.ukim.finki.wp.exam.example.repository.CategoryRepository;
import mk.ukim.finki.wp.exam.example.repository.ProductRepository;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // dopisana anotacija za @Service
// dopisano implements ProductService
public class ProductServiceImpl implements ProductService {

    // dopisano
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository; // zavisime podolu od ova.

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // dopisano
    @Override
    public List<Product> listAllProducts() {
        return this.productRepository.findAll();
    }
    // dopisano
    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(InvalidProductIdException::new);
    }
    // dopisano
    @Override
    public Product create(String name, Double price, Integer quantity, List<Long> categoryIds) {
        List<Category> categories = this.categoryRepository.findAllById(categoryIds);
        // categories ni e potrebna za da gi najdime byId site prateni vo konstruktorov
        // pa prajme objekt od product so categories.
        Product product = new Product(name, price, quantity, categories);
        return this.productRepository.save(product);
    }
    // dopisano
    @Override
    public Product update(Long id, String name, Double price, Integer quantity, List<Long> categoryIds) {
        Product product = this.findById(id); // go zemame produktot po ID i setirame ime, price, quantity.
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        // Jpa ke se pogrizi za produktite i kategoriite koi so se dodaeni do sega
        List<Category> categories = this.categoryRepository.findAllById(categoryIds);
        product.setCategories(categories);
        return this.productRepository.save(product);
    }
    // dopisano
    @Override
    public Product delete(Long id) {
        Product product = this.findById(id); // skoro sekogas go koristime findById za da go najdime soodvetnio element na koj so
        // sakame da rabotime
        this.productRepository.delete(product);
        return product;
    }
    // dopisano
    @Override
    // posledno baranje, za filtriranje na podatoci t.e pravenje na filter.
    // za ova ni treba specijalni metodi vo ProductRepository koj so gi dodavam tamu.
    public List<Product> listProductsByNameAndCategory(String name, Long categoryId) {
        // TODO: implement it
        String nameLike = "%" + name + "%";
        Category category =categoryId != null ? this.categoryRepository.findById(categoryId).orElse((Category)null) : null;
       if(name!=null && category!=null) {
           return this.productRepository.findAllByNameLikeAndCategoriesContaining(nameLike, category);
       } else if(name!=null ){
          return this.productRepository.findAllByNameLike(nameLike);
       } else if(category!=null){
           return this.productRepository.findAllByCategoriesContains(category);
       } else{
           return this.productRepository.findAll();
       }
    }
}
