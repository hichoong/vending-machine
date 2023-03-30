package com.ezace.vendingmachine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    private Long id;
    private String name;
    private int price;
    private int count;
}
