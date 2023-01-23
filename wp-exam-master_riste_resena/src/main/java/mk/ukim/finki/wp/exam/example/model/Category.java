package mk.ukim.finki.wp.exam.example.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


// prvo baranje od TEHNICKO upatstvo
// pravime mapiranja
@Entity // kazuvame deka stanuva zbor za Entitet, preku JPA go postavuva kontekstot
// i go zacuvuva.
public class Category {


    // dopisano
    public Category(){
    }

    //dopisano
    public Category(String name){
        this.name = name;
    }
    @Id  // za prvoto tehnicko baranje
    @GeneratedValue // isto
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
