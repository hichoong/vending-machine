package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.vo.GoodsVo;
import com.ezace.vendingmachine.service.GoodsService;
import com.ezace.vendingmachine.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GoodsContoller {
    private final GoodsService goodsService;
    private final SalesService salesService;

    @GetMapping("/")
    public List<GoodsVo> findAllGoods() {
        return goodsService.findAllGoods();
    }

    @PatchMapping("/{id}")
    public void purchase(@PathVariable Long id){
        goodsService.modifyGoods(id);

    }
}
