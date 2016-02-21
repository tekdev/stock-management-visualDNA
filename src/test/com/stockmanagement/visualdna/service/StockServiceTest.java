package com.stockmanagement.visualdna.service;

import com.stockmanagement.visualdna.Intergration.ItemRepository;
import com.stockmanagement.visualdna.domain.Category;
import com.stockmanagement.visualdna.domain.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mo on 20/02/2016.
 */
public class StockServiceTest {
    private ItemRepository itemRepository = mock(ItemRepository.class);
    private StockServiceImpl stockService;

    @Before
    public void setup() {
        this.stockService = new StockServiceImpl();
        this.stockService.setItemRepository(itemRepository);
    }

    @Test
    public void addItem() {
        Item expectedItem = new Item(2l, "tomatoes", 4.95, 45, Category.VEG);
        when(this.itemRepository.save(any(Item.class))).thenReturn(expectedItem);
        Item actual = this.stockService.addItem(expectedItem);
        assertEquals(expectedItem.getId(), actual.getId());
        assertEquals(expectedItem.getName(), actual.getName());
    }

    @Test
    public void findByCategoryNotFound() {
        Item expectedItem = new Item(2l, "tomatoes", 4.95, 45, Category.VEG);
        when(this.itemRepository.save(any(Item.class))).thenReturn(null);
        Optional<List<Item>> actual = this.stockService.getItemsByCategoryId(Category.FISH);
        assertTrue(actual.get().isEmpty());
    }

    @Test
    public void getLowStockItems() {
        List<Item> expectedItems = new ArrayList<Item>() {{
            add(new Item("tomatoes", 4.95, 4, Category.VEG));
            add(new Item("orange", 0.99, 9, Category.FRUIT));
        }};
        when(this.itemRepository.findLowQuantityStock()).thenReturn(expectedItems);
        Optional<List<Item>> actual = this.stockService.getlowStockItems();
        assertTrue(!actual.get().isEmpty());
        assertTrue(actual.get().stream().anyMatch(item -> item.getName().equals("tomatoes")));
        assertTrue(actual.get().stream().anyMatch(item -> item.getName().equals("orange")));
    }
}
