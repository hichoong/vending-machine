package com.ezace.vendingmachine.domain.vo;

import lombok.*;

@Data
public class GoodsVo {
    private Long id;
    private String name;
    private int price;
    private int count;
    private String image;
}