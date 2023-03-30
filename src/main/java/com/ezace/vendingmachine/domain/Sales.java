package com.ezace.vendingmachine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    private Long id;
    private Long goodsId;
    private Timestamp salesDate;
}
