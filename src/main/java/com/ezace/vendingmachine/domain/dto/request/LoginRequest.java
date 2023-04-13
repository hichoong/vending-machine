package com.ezace.vendingmachine.domain.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String name;
    private String password;
}
