package com.example.simpleProducts.service;

import com.example.simpleProducts.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

}
