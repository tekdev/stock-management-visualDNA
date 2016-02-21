package com.stockmanagement.visualdna.Intergration;

import com.stockmanagement.visualdna.domain.Category;
import com.stockmanagement.visualdna.domain.Item;

import java.util.HashSet;

/**
 * Created by modev on 21/02/2016.
 */
public class DatabaseSeed {

    public static HashSet<Item> getItems (){
        return new HashSet<Item>(){{
            add(new Item( "tomatoes",  4.95,  45, Category.VEG));
            add(new Item( "orange",  0.99,  15, Category.FRUIT));
            add(new Item( "apple",  0.99,  9, Category.FRUIT));
            add(new Item( "salmon",  3.45,  23,  Category.FISH));
            add(new Item( "cod",  2.50,  18, Category.FISH));
            add(new Item( "beef", 14.95, 45, Category.MEAT));
            add(new Item( "chicken", 8.99, 8, Category.MEAT));
            add(new Item( "steak", 11.36, 11, Category.MEAT));
        }};
    }
}
