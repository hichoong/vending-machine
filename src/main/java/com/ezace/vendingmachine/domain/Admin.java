package com.ezace.vendingmachine.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {
    private Long id;
    private String name;
    private String password;
}
