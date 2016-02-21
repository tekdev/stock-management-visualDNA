package com.stockmanagement.visualdna.service;

import com.stockmanagement.visualdna.domain.Category;
import com.stockmanagement.visualdna.domain.Item;

import java.util.List;
import java.util.Optional;

/**
 * Created by modev on 20/02/2016.
 */

public interface StockService {
    Optional<Item> findById(long id);

    Optional<List<Item>> getItemsByCategoryId(Category category);

    Optional<List<Item>> getlowStockItems();

    Item addItem(Item item);

    Optional<Item> updateStockQuantity(Item toUpdate, int quantity, boolean deduction);

    void deleteItem(long itemId);
}
