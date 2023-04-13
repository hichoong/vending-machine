package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.dto.request.SelectDate;
import com.ezace.vendingmachine.domain.dto.response.SalesResponse;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface SalesMapper {

    List<SalesResponse> findAllSales();
    Page<SalesResponse> findAllByPagingSales();
    void insertSales(String name, int price);
    List<SalesResponse> findBySalesDate(SelectDate selectDate);
    Page<SalesResponse> findBySalesDate(String firstChoiceDate);
    List<SalesResponse> findByGoods();
    List<SalesResponse> findByGoodsChart();
}
