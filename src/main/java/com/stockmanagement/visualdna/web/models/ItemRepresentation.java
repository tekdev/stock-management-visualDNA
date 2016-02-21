package com.stockmanagement.visualdna.web.models;

import com.stockmanagement.visualdna.domain.Category;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Mo on 20/02/2016.
 */
@XmlRootElement(name = "item")
@XmlType(propOrder = {"id","name", "price", "quantity", "category"})
public class ItemRepresentation {
    private long id;
    @NotNull
    private String name ;
    @DecimalMin("0.1")
    private double price;
    @Min(1)
    private int quantity;
    private Category category;

    public ItemRepresentation() {
    }

    public ItemRepresentation(long id ,String name, double price, int quantity, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
