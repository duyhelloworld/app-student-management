package model;

public class Product {
    private Long id;
    private String name;
    private int price;
    
    public Product() {
    }
    
    public Product(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product id(Long id) {
        setId(id);
        return this;
    }

    public Product name(String name) {
        setName(name);
        return this;
    }

    public Product price(int price) {
        setPrice(price);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            "id = '" + getId() + "'" +
            ", name='" + getName() + "'" +
                ", price='" + getPrice() + "'" +
            "}";
    }

}
