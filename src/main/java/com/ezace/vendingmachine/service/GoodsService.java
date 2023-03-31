package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.vo.GoodsVo;
import com.ezace.vendingmachine.repository.GoodsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsMapper goodsMapper;

    public List<GoodsVo> getGoodsList() {
        return goodsMapper.getGoodsList();
    }

}
