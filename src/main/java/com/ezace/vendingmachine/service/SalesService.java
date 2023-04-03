package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.vo.SalesVo;
import com.ezace.vendingmachine.repository.SalesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesMapper salesMapper;

    public List<SalesVo> findAllSales() {
        return salesMapper.findAllSales();
    }
}
