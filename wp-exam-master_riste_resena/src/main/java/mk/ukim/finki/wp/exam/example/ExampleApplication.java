package mk.ukim.finki.wp.exam.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean // dopisano seto ova, ni treba Factory Metodot koj e anotiran so Bean, za da enkodirame passwords.
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
