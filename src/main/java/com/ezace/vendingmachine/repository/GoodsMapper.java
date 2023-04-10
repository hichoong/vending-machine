package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {
    List<GoodsVo> findAllGoods();

    void modifyGoods(Long id);

    void insertGoods(GoodsVo goodsVo);

    GoodsVo findById(Long id);

    void deleteGoods();
}
