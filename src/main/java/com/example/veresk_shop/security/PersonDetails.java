package com.example.veresk_shop.security;


import com.example.veresk_shop.models.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

//информ.о конкретном пол-те, кот. хочет авторизоваться
public class PersonDetails implements UserDetails {
    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    public Person getPerson(){
        return this.person;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }


//вернуть пароля от пользователя
    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    //вернуть логин польз-ля
    @Override
    public String getUsername() {
        return this.person.getLogin();
    }

// Аккаунт действителен
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

// Аккаунт не заблокирован
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

// Пароль является действительным
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

// Аккаунт активен
    @Override
    public boolean isEnabled() {
        return true;
    }
}
