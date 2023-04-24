package com.ezace.vendingmachine.event;

import lombok.Getter;
@Getter
public class PurchaseEvent {
    private String goodsName;

    public PurchaseEvent(String goodsName) {
        this.goodsName = goodsName;
    }
}
