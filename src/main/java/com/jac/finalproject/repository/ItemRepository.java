package com.jac.finalproject.repository;

import com.jac.finalproject.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {


    @Query(value = "SELECT item_id FROM jac_order_item WHERE order_id = :orderId", nativeQuery = true)
    List<Long> getItemIdsByOrderId(Long orderId);
}
