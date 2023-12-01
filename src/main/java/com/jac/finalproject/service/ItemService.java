package com.jac.finalproject.service;

import com.jac.finalproject.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item createItem(Item item);

    Optional<Item> getItemById(Long itemId);

    List<Item> getAllItems();

    Item updateItem(Item item);

    void deleteItem(Long itemId);

    List<Long> getItemIdsByOrderId(Long orderId);
}
