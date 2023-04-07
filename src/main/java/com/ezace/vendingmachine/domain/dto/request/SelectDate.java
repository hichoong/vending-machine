package com.ezace.vendingmachine.domain.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SelectDate {
    private Timestamp startDate;
    private Timestamp endDate;
}
