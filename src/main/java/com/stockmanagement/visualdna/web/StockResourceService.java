package com.stockmanagement.visualdna.web;

import com.stockmanagement.visualdna.domain.Category;
import com.stockmanagement.visualdna.domain.Item;
import com.stockmanagement.visualdna.service.StockService;
import com.stockmanagement.visualdna.web.models.ItemRepresentation;
import com.stockmanagement.visualdna.web.models.ItemsRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Optional;


/**
 * Created by Mo on 20/02/2016.
 */
@RestController
@RequestMapping("/stock")
public class StockResourceService {

    private StockService stockService;

    @Autowired
    public StockResourceService(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ItemsRepresentation getItemsByCategoryId(@PathVariable Category category) {
        return stockService.getItemsByCategoryId(category)
                .map(items -> ResourceAdaptor.getItemsRepresentations(items))
                .orElse(new ItemsRepresentation(Collections.emptyList()));
    }

    @RequestMapping(value = "/low", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ItemsRepresentation getLowStock() {
        return stockService.getlowStockItems()
                .map(items -> ResourceAdaptor.getItemsRepresentations(items))
                .orElse(new ItemsRepresentation(Collections.emptyList()));
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON)
    public ItemRepresentation addNewItem(@Valid @RequestBody ItemRepresentation representation) {
        Item item = ResourceAdaptor.getItemFromRepresentation(representation);

        return ResourceAdaptor.getItemRepresentation(stockService.addItem(item));
    }

    @RequestMapping(value = "/update/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public ItemRepresentation updateQuantity(@PathVariable long id,
                                             @RequestParam int quantity,
                                             @RequestParam(defaultValue = "false") boolean deduct) {
        Optional<Item> toUpdate = stockService.findById(id);
        if (!toUpdate.isPresent()) {
            return null;
        }
        return stockService.updateStockQuantity(toUpdate.get(), quantity, deduct)
                .map(item -> ResourceAdaptor.getItemRepresentation(item))
                .orElse(null);
    }

    @RequestMapping(value = "/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON)
    public ItemRepresentation deleteItem(@PathVariable long id) {
        Optional<Item> toDelete = stockService.findById(id);
        if (toDelete.isPresent()) stockService.deleteItem(toDelete.get().getId());
        return toDelete.map(item -> ResourceAdaptor.getItemRepresentation(item))
                .orElse(null);
    }
}
