package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.dto.request.SelectDate;
import com.ezace.vendingmachine.domain.dto.response.SalesResponse;
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
    public List<SalesResponse> findBySalesDate(SelectDate selectDate) {
        List<SalesResponse> result = salesMapper.findBySalesDate(selectDate);
        return result;
    }

    public Page<SalesResponse> findBySalesDate(int pageNum, String firstChoiceDate) {
        PageHelper.startPage(pageNum, 10);
        return salesMapper.findBySalesDate(firstChoiceDate);
    }
    public List<SalesResponse> findBySalesDate(String firstChoiceDate) {
        return salesMapper.findBySalesDate(firstChoiceDate);
    }

    public List<SalesResponse> findByGoods() {
        return salesMapper.findByGoods();
    }
}
