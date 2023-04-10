package com.ezace.vendingmachine.domain.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BuyGoods {
    private Long id;
    private int money;
}
