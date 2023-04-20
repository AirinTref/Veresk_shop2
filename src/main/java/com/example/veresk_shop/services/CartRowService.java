package com.example.veresk_shop.services;

import com.example.veresk_shop.repositories.CartRowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CartRowService {
    private final CartRowRepository cartRowRepository;

    @Transactional
    public void deleteCartRowById(Integer id){
        cartRowRepository.deleteById(id);
    }


}
