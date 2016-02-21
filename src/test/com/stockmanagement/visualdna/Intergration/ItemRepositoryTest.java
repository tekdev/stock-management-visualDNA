package com.stockmanagement.visualdna.Intergration;

import com.stockmanagement.visualdna.TestConfiguration;
import com.stockmanagement.visualdna.domain.Category;
import com.stockmanagement.visualdna.domain.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by Mo on 20/02/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfiguration.class})
public class ItemRepositoryTest {
    private static final Logger log = LoggerFactory.getLogger(ItemRepositoryTest.class);
    private ItemRepository itemRepository;
    private HashSet<Item> items;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Before
    public void setUp() {
        itemRepository.deleteAll();
        log.info("initialised the database for test data, Count = " + itemRepository.count());
        assertEquals(itemRepository.count(), 0);
        this.items = getItems();
        itemRepository.save(items);
    }

    @Test
    public void checkAllItemsPersisted() {
        assertEquals(itemRepository.count(), items.size());
    }

    @Test
    public void findByCategory() {
        List<Item> foundItems = itemRepository.findByCategory(Category.FISH);
        assertEquals(foundItems.size(), 2);
        assertTrue(foundItems.stream().anyMatch(item -> item.getName().equals("salmon")));
    }

    @Test
    public void updateItem() {
        Item item = itemRepository.findByName("orange");
        assertNotNull(item);
        //persisting same changes
        item.setStockQuantity(item.getStockQuantity() + 1);
        itemRepository.save(item);

        //retreiving again
        Item updatedItem = itemRepository.findOne(item.getId());
        assertNotNull(updatedItem);
        assertEquals(16, updatedItem.getStockQuantity());
    }

    @Test
    public void deleteItem() {
        Item item = itemRepository.findByName("cod");
        assertNotNull(item);
        // deleting
        itemRepository.delete(item);

        //asserting does not exist
        Item deleted = itemRepository.findOne(item.getId());
        assertNull(deleted);
    }

    private HashSet<Item> getItems() {
        return new HashSet<Item>() {{
            add(new Item("tomatoes", 4.95, 45, Category.VEG));
            add(new Item("orange", 0.99, 15, Category.FRUIT));
            add(new Item("apple", 0.99, 9, Category.FRUIT));
            add(new Item("salmon", 3.45, 23, Category.FISH));
            add(new Item("cod", 2.50, 18, Category.FISH));
            add(new Item("beef", 14.95, 45, Category.MEAT));
            add(new Item("chicken", 8.99, 8, Category.MEAT));
            add(new Item("steak", 11.36, 11, Category.MEAT));
        }};
    }

}
