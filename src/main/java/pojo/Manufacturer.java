package pojo;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "manufacturer")
public class Manufacturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String country;

    public Manufacturer() {
    }

    public Manufacturer(String name, String country, int id) {
        this.name = name;
        this.country = country;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
