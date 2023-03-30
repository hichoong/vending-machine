package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.repository.SalesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesMapper salesMapper;
}
