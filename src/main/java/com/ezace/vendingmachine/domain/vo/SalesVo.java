package com.ezace.vendingmachine.domain.vo;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class SalesVo {
    private Long id;
    private Long goodsId;
    private LocalDateTime salesDate;
}
