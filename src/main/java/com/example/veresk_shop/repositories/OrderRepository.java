package com.example.veresk_shop.repositories;

import com.example.veresk_shop.enumm.Status;
import com.example.veresk_shop.models.Order;
import com.example.veresk_shop.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByPerson(Person person);

    @Query(value = "select o from Order o where o.status =:orderStatus")
    List<Order> getAllByOrderStatus(Status orderStatus);

    @Query(value = "select o from Order o where o.number =:searchOrder")
    List<String> findByNumberEndingWithIgnoreCase(String searchOrder);

}
