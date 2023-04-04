package com.ezace.vendingmachine.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyGoods {
    private String name;
    private int count;
}
