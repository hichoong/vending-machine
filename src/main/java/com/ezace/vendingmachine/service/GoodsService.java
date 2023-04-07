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

    public List<GoodsVo> findAllGoods() {
        return goodsMapper.findAllGoods();
    }

    public void modifyGoods(Long id) {
        goodsMapper.modifyGoods(id);
    }

    public void insertGoods(GoodsVo goodsVo) {
        goodsMapper.insertGoods(goodsVo);
    }

    public GoodsVo findById(Long id) {
       return goodsMapper.findById(id);
    }

    public void deleteGoods(){
        goodsMapper.deleteGoods();
    }

}
