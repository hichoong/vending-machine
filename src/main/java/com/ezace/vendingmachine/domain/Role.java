package com.ezace.vendingmachine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN_ROLE"), USER("USER_ROLE");
    public String value;
}
