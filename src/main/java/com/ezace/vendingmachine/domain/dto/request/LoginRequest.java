package com.ezace.vendingmachine.domain.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginRequest {
    private String name;
    private String password;
}
