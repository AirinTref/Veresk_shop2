package com.example.veresk_shop.repositories;

import com.example.veresk_shop.models.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRowRepository extends JpaRepository<OrderRow, Integer> {

    @Query(value = "select * from order_rows where order_id =:orderId", nativeQuery = true)
    List<OrderRow> findRowsByOrderId(Integer orderId);
}
