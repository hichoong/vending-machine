package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.vo.GoodsVo;
import com.ezace.vendingmachine.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexContoller {
    private final GoodsService goodsService;

    @GetMapping("/main")
    public String main(Model model) {
        log.info("main페이지 불러오기");
        model.addAttribute("goodsList", goodsService.findAllGoods());
        return "main";
    }

    @GetMapping("/index")
    public String index(Model model) {
        log.info("index페이지 불러오기");
        List<GoodsVo> goodsList = goodsService.findAllGoods();
        model.addAttribute("goodsList", goodsList);
        return "vending-machine";
    }

    @GetMapping("/just")
    public String just(Model model) {
        log.info("just페이지 호출");
        List<GoodsVo> goodsList = goodsService.findAllGoods();
        model.addAttribute("goodsList", goodsList);
        return "just";
    }
}
