package com.stockmanagement.visualdna.web;

import com.stockmanagement.visualdna.domain.Category;
import com.stockmanagement.visualdna.domain.Item;
import com.stockmanagement.visualdna.web.models.ItemRepresentation;
import com.stockmanagement.visualdna.web.models.ItemsRepresentation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by mo on 21/02/2016.
 */
public class ResourceAdaptorTest {

    @Test
    public void getItemRepresentation() {
        Item expectedItem = new Item(2l, "tomatoes", 4.95, 45, Category.VEG);
        ItemRepresentation itemRepresentation = ResourceAdaptor.getItemRepresentation(expectedItem);
        assertEquals(2l, itemRepresentation.getId());
        assertEquals("tomatoes", itemRepresentation.getName());
        assertEquals(4.95, itemRepresentation.getPrice());
        assertEquals(45, itemRepresentation.getQuantity());
        assertEquals(Category.VEG, itemRepresentation.getCategory());
    }

    @Test
    public void getItemsRepresentation() {
        ItemsRepresentation itemsRepresentation = ResourceAdaptor.getItemsRepresentations(getItems());
        assertTrue(itemsRepresentation.getItems().stream().anyMatch(item -> item.getName().equals("tomatoes")));
        assertTrue(itemsRepresentation.getItems().stream().anyMatch(item -> item.getName().equals("orange")));
        assertTrue(itemsRepresentation.getItems().stream().anyMatch(item -> item.getName().equals("apple")));

    }

    private List<Item> getItems() {
        return new ArrayList<Item>() {{
            add(new Item(1l, "tomatoes", 4.95, 45, Category.VEG));
            add(new Item(2l, "orange", 0.99, 15, Category.FRUIT));
            add(new Item(3l, "apple", 0.99, 9, Category.FRUIT));
        }};
    }
}
