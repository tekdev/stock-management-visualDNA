package com.stockmanagement.visualdna.domain;

import javax.persistence.*;

/**
 * Created by Mo on 20/02/2016.
 */
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int stockQuantity;
    @Column(nullable = false)
    private Category category;

    protected Item() {
    }

    public Item(String name, double price, int stockQuantity, Category category) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public Item(long id, String name, double price, int stockQuantity, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append("Item[" + "" +
                "id= " + id + " ," +
                "name= " + name + " ," +
                "price= " + price + " ," +
                "stockQuantity= " + stockQuantity + " ," +
                "category= " + category + "]");
        return st.toString();
    }
}
