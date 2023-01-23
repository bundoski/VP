package mk.ukim.finki.wp.exam.example.repository;

import mk.ukim.finki.wp.exam.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// Baranje 3. so menuvanje na class vo interface, i dodavanje na extends JpaRepository<User,String>
// e zavrseno, i dopisana metoda findByUsername zaradi to sho bese potrebna.

// smeniv class vo interface, vo repository delo site klasi treba da bida
// interfejsi, kaj so isto ima extends JpaRepository<User, String>, String znaci deka primaren
// kluc na User e od Type string ( BITNO )
public interface UserRepository extends JpaRepository<User, String> {


    // ni treba metodata za loadUserByUsername.
    Optional<User> findByUsername(String username);
}
