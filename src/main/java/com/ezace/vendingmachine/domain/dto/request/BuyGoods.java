package com.ezace.vendingmachine.domain.dto.request;

import lombok.Data;

@Data
public class BuyGoods {
    private Long id;
    private int money;
}
