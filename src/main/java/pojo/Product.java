package pojo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    @Column(name = "created_date")
    private Date createdDate;
    private boolean active;
    @ManyToOne(fetch = FetchType.LAZY) // default: EAGER
    @JoinColumn(name = "category_id")
    private Category category;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prod_man",
        joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "manufacturer_id")}
    )
    private Set<Manufacturer> manufacturers = new HashSet<Manufacturer>();

    public Product() {
    }

    public Product(int id, String description, String name, BigDecimal price, String image,
                   Date createdDate, boolean active, Category category) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.image = image;
        this.createdDate = createdDate;
        this.active = active;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(Set<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }
}
