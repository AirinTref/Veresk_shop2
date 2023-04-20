package com.example.veresk_shop.repositories;


import com.example.veresk_shop.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ищем в сущности персон-логин
//как найден-объект человека
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByLogin(String login);
}

