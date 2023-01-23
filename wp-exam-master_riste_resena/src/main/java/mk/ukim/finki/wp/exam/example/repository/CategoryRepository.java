package mk.ukim.finki.wp.exam.example.repository;


import mk.ukim.finki.wp.exam.example.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


// Baranje 3. so menuvanje na class vo interface, i dodavanje na extends JpaRepository<Category, Long>
// e zavrseno, i dopisana metoda findByUsername zaradi to sho bese potrebna.

// smeneto od class vo interface
// Category, Long (Tip na identifikator na
// postaveniot kluc ( se proveruva vo Category,
// i to so e anotirano so @Id
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
