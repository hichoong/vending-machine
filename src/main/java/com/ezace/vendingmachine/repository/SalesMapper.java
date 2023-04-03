package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.vo.SalesVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesMapper {

    List<SalesVo> findAllSales();

    void insertSales(Long id);
}
