package com.ezace.vendingmachine.domain.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SelectDate {
    private LocalDateTime firstChoiceDate;
    private LocalDateTime secondChoiceDate;
}
