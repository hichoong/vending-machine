package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.vo.GoodsVo;
import com.ezace.vendingmachine.service.GoodsService;
import com.ezace.vendingmachine.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goods")
@Slf4j
public class GoodsApiContoller {
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
