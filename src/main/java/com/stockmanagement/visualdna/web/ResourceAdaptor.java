package com.stockmanagement.visualdna.web;

import com.stockmanagement.visualdna.domain.Item;
import com.stockmanagement.visualdna.web.models.ItemRepresentation;
import com.stockmanagement.visualdna.web.models.ItemsRepresentation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mo on 20/02/2016.
 */
public class ResourceAdaptor {
    static ItemsRepresentation getItemsRepresentations(List<Item> items) {
        List<ItemRepresentation> itemRepresentationList = items.stream().map(i -> getItemRepresentation(i)).collect(Collectors.toList());
        return new ItemsRepresentation(itemRepresentationList);
    }

    static ItemRepresentation getItemRepresentation(Item item) {
        return new ItemRepresentation(item.getId(),item.getName(),
                item.getPrice(), item.getStockQuantity(),
                item.getCategory());
    }

    static Item getItemFromRepresentation(ItemRepresentation representation) {
        return new Item(representation.getName(),
                representation.getPrice(), representation.getQuantity(),
                representation.getCategory());
    }
}
