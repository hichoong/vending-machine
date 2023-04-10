package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.dto.request.BuyGoods;
import com.ezace.vendingmachine.domain.dto.response.GoodsResponse;
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

    @GetMapping("/goods")
    public List<GoodsVo> findAllGoods() {
        return goodsService.findAllGoods();
    }

    @PatchMapping("/goods/{id}")
    public void purchase(@PathVariable Long id){
        GoodsVo goodsVo = goodsService.findById(id);
        if (goodsVo != null) {
            goodsService.modifyGoods(id);
        }
    }

    @PostMapping("/goods/purchase")
    public @ResponseBody GoodsResponse purchase(@RequestBody BuyGoods buyGoods) {
        log.info("받은 정보 값 = {}, {}",buyGoods.getId(), buyGoods.getMoney() );
        GoodsResponse goodsResponse = goodsService.purchase(buyGoods);
        return goodsResponse;
    }
}
