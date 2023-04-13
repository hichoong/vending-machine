package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.vo.GoodsVo;
import com.ezace.vendingmachine.service.AdminService;
import com.ezace.vendingmachine.service.GoodsService;
import com.ezace.vendingmachine.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainContoller {
    private final GoodsService goodsService;
    private final AdminService adminService;
    private final SalesService salesService;

    @GetMapping("/main")
    public String main(Model model) {
        log.info("main페이지 호출");
        List<GoodsVo> goodsList = goodsService.findAllGoods();
        log.info("상품목록 : {}", goodsList.stream().map(a -> a.getName()).collect(Collectors.toList()));
        model.addAttribute("goodsList", goodsList);
        return "vending-machine";
    }
}
