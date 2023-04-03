package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<GoodsVo> findAllGoods();

    GoodsVo modifyGoods(Long id);
}
