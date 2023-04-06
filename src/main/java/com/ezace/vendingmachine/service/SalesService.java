package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.dto.response.SalesResponse;
import com.ezace.vendingmachine.domain.vo.SalesVo;
import com.ezace.vendingmachine.repository.SalesMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesMapper salesMapper;

    public List<SalesResponse> findAllSales() {
        return salesMapper.findAllSales();
    }
    public Page<SalesResponse> findAllByPagingSales(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        return salesMapper.findAllByPagingSales();
    }





}
