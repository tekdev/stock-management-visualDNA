package com.stockmanagement.visualdna.web.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by modev on 20/02/2016.
 */
@XmlRootElement(name = "items")
@XmlType(propOrder = {"items"})
public class ItemsRepresentation {

    List<ItemRepresentation> items;

    public ItemsRepresentation(List<ItemRepresentation> items) {
        this.items = items;
    }

    public List<ItemRepresentation> getItems() {
        return items;
    }

    public void setItems(List<ItemRepresentation> items) {
        this.items = items;
    }
}
