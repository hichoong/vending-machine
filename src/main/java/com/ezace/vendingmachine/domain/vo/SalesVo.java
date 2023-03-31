package com.ezace.vendingmachine.domain.vo;

import lombok.*;

import java.sql.Timestamp;

@Data
public class SalesVo {
    private Long id;
    private Long goodsId;
    private Timestamp salesDate;
}
