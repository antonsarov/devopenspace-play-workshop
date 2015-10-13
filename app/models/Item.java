package models;

import javax.persistence.*;

import com.avaje.ebean.Model;

import java.math.BigDecimal;

@Entity
public class Item extends Model {

    public static Finder<Long, Item> find = new Finder<Long,Item>(Item.class);

    @Id
    private Long id;

    private String name;

    private String image;

    private BigDecimal price;

    public Item(String name, String image, BigDecimal price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

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
}
