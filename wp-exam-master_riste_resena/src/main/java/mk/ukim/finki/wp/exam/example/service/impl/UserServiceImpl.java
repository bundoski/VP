package mk.ukim.finki.wp.exam.example.service.impl;


import mk.ukim.finki.wp.exam.example.model.Role;
import mk.ukim.finki.wp.exam.example.model.User;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.wp.exam.example.repository.UserRepository;
import mk.ukim.finki.wp.exam.example.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;


// tehnicko baranje pod 2., ovde implementirame potrebni servisi, itn, ne samo vo ovaa tuku i vo ostanatite
// dve klasi.
@Service // dopisano, za da koristi Injection
        //                              dodavame UserDetailsService za
//                                   implementacija na loadUserByUsername metod
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;     // dopisano, potrebno za vtoroto baranje
    private final PasswordEncoder passwordEncoder;    // dopisano, potrebno za vtoroto baranje

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }     // dopisano za passwordEncoder vo ExampleApp treba
            // da se dodaj @Bean, proveri.


    @Override // dopisano, override na create metod vo userservice
    public User create(String username, String password, Role role) {
        String encryptedPassword = this.passwordEncoder.encode(password);
        User user = new User(username, encryptedPassword, role); // so ova postavuvame encrypted password.
        return this.userRepository.save(user);
    }

    // dopisano
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(InvalidUsernameException::new);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Stream.of(new SimpleGrantedAuthority(user.getRole().toString())).collect(Collectors.toList()));
        return userDetails;
    }

}
