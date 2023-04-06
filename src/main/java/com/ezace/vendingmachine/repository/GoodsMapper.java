package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsMapper {
    List<GoodsVo> findAllGoods();

    GoodsVo modifyGoods(Long id);
}
