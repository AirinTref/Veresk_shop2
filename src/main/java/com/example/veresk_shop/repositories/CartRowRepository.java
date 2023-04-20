package com.example.veresk_shop.repositories;


import com.example.veresk_shop.models.CartRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRowRepository extends JpaRepository<CartRow, Integer> {

}
