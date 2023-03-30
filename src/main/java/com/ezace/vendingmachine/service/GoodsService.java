package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.repository.GoodsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsMapper goodsMapper;
}
