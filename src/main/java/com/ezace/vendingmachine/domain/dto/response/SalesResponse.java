package com.ezace.vendingmachine.domain.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SalesResponse {
    private Long id;
    private String name;
    private int count;
    private Timestamp salesDate;
}
