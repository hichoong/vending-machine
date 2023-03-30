package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<Goods> getGoodsList();
}
