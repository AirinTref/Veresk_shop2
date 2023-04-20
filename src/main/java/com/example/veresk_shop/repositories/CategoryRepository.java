package com.example.veresk_shop.repositories;


import com.example.veresk_shop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    com.example.veresk_shop.models.Category findByName(String name);
}
