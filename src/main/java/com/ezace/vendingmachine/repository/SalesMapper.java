package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.dto.request.SelectDate;
import com.ezace.vendingmachine.domain.dto.response.SalesResponse;
import com.ezace.vendingmachine.domain.vo.SalesVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface SalesMapper {

    List<SalesResponse> findAllSales();
    Page<SalesResponse> findAllByPagingSales();
    void insertSales(Long id);
    List<SalesResponse> findBySalesDate(SelectDate selectDate);
    Page<SalesResponse> findBSalesDatePaging(SelectDate selectDate);

    Page<SalesResponse> findBySalesDate(LocalDateTime firstChoiceDate);
}
