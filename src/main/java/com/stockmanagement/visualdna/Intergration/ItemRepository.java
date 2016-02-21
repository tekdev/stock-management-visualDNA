package com.stockmanagement.visualdna.Intergration;

import com.stockmanagement.visualdna.domain.Category;
import com.stockmanagement.visualdna.domain.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by modev on 20/02/2016.
 */

public interface ItemRepository extends CrudRepository<Item , Long> {
    List<Item> findByCategory(Category category);

    Item findByName(String name);

    @Query("SELECT i FROM Item i WHERE i.stockQuantity < 10")
    List<Item> findLowQuantityStock();
}
