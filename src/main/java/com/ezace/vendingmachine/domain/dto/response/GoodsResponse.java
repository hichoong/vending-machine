package com.ezace.vendingmachine.domain.dto.response;

import lombok.Data;

@Data
public class GoodsResponse {
    private Long id;
    private String name;
    private int price;
    private int count;
    private String image;
    private int money;
    private String msg;
}
