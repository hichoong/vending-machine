package com.ezace.vendingmachine.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexContoller {
    @GetMapping("/")
    public String main() {
        log.info("메인페이지 불러오기");
        return "main";
    }
}
