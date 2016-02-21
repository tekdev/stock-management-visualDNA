package com.stockmanagement.visualdna.service;

import com.stockmanagement.visualdna.domain.Category;
import com.stockmanagement.visualdna.domain.Item;
import com.stockmanagement.visualdna.Intergration.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by modev on 20/02/2016.
 */
@Service
public class StockServiceImpl implements StockService {

    private ItemRepository itemRepository;
    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Optional<Item> findById(long id) {
        return Optional.ofNullable(itemRepository.findOne(id));
    }

    @Override
    public Optional<List<Item>> getItemsByCategoryId(Category category) {
        return Optional.ofNullable(itemRepository.findByCategory(category));
    }

    @Override
    public Optional<List<Item>> getlowStockItems() {
        return Optional.ofNullable(itemRepository.findLowQuantityStock());
    }

    @Override
    public Item addItem(Item newItem) {
        //doing an update if the item already exist in the db
        Item item = Optional.ofNullable(itemRepository.findByName(newItem.getName()))
                .map(i -> {
                    i.setStockQuantity(i.getStockQuantity() + newItem.getStockQuantity());
                    return i;
                }).orElse(newItem);
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> updateStockQuantity(Item toUpdate, int quantity, boolean deduction) {

        if (!deduction) {
            toUpdate.setStockQuantity(toUpdate.getStockQuantity() + quantity);
        } else if (toUpdate.getStockQuantity() >= quantity) {
            toUpdate.setStockQuantity(toUpdate.getStockQuantity() - quantity);
        }

        // doing a delete the stock level reach 0
        if (toUpdate.getStockQuantity() == 0) {
            itemRepository.delete(toUpdate.getId());
            return Optional.empty();
        }
        return Optional.of(itemRepository.save(toUpdate));
    }

    @Override
    public void deleteItem(long itemId) {
        itemRepository.delete(itemId);
    }
}
