package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexContoller {
    private final GoodsService goodsService;

    @GetMapping("/main")
    public String main(Model model) {
        log.info("main페이지 불러오기");
        model.addAttribute("goodsList", goodsService.getGoodsList());
        return "main";
    }

    @GetMapping("/index")
    public String main1() {
        log.info("index페이지 불러오기");
        return "index";
    }
}
