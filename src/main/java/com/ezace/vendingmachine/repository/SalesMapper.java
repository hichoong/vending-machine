package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.dto.response.SalesResponse;
import com.ezace.vendingmachine.domain.vo.SalesVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Repository
public interface SalesMapper {

    List<SalesResponse> findAllSales();

    void insertSales(Long id);

    Page<SalesResponse> findAllByPagingSales();
}
