package com.ezace.vendingmachine.domain.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SalesResponse {
    private Long id;
    private String name;
    private int count;
    private int price;
    private LocalDateTime salesDate;
}
