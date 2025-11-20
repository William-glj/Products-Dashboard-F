package com.example.simpleProducts.service;

import com.example.simpleProducts.repository.StockRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class StockService {

    @Autowired
    private StockRepository stockRepository;
}
