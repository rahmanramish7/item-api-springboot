package com.example.itemapi.service;

import com.example.itemapi.exception.ItemNotFoundException;
import com.example.itemapi.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {

    private final List<Item> items = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Item addItem(Item item) {
        item.setId(idCounter.getAndIncrement());
        items.add(item);
        return item;
    }

    public Item getItemById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ItemNotFoundException("Item not found with id: " + id));
    }
}
