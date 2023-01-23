package mk.ukim.finki.wp.exam.example.repository;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// Baranje 3. so menuvanje na class vo interface, i dodavanje na extends JpaRepository<Product, Long>
// e zavrseno, i dopisana metoda findByUsername zaradi to sho bese potrebna.
// isto smeneta klasa vo interface, i da extend JpaRepository<Product, Long>
public interface ProductRepository extends JpaRepository<Product, Long> {

    // metodi potrebni za poslednoto baranje, filtriranje.
    List<Product> findAllByNameLikeAndCategoriesContaining(String name, Category category);
    List<Product> findAllByNameLike(String name);
    List<Product> findAllByCategoriesContains(Category category);


}
