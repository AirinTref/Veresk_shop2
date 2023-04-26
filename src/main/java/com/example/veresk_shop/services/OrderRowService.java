package com.example.veresk_shop.services;

import com.example.veresk_shop.models.OrderRow;
import com.example.veresk_shop.repositories.OrderRowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderRowService {
    private final OrderRowRepository orderRowRepository;

    public List<OrderRow> getRowsByOrderId(Integer orderId){
        return orderRowRepository.findRowsByOrderId(orderId);
    }


}
