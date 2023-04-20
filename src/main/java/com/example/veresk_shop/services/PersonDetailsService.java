package com.example.veresk_shop.services;

import com.example.veresk_shop.models.Person;
import com.example.veresk_shop.models.Product;
import com.example.veresk_shop.repositories.PersonRepository;
import com.example.veresk_shop.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//отлич.от обычного сервисного слоя, т.к. тут должен получать информацию о пользователе и работе с ним- аутентификация UserDetailsService
@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;


    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Получаем пользователя из таблицы по логину с формы аутентификации
        Optional<Person> person = personRepository.findByLogin(username);
        // Если пользователь не был найден
        if(person.isEmpty()){
            // Выбрасываем исключение что данный пользователь не найден
            // Данное исключение будет поймано Spring Security и сообщение будет выведено на страницу
            throw new UsernameNotFoundException("Пользователь не найден");
        }

//если польз. найден, то в personDetails
        return new PersonDetails(person.get());
    }


}
