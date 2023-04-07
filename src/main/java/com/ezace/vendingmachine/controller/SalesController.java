package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.dto.request.SelectDate;
import com.ezace.vendingmachine.domain.dto.response.SalesResponse;
import com.ezace.vendingmachine.service.SalesService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SalesController {

    private final SalesService salesService;
    @GetMapping("/sales")
    public String salesList(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum) {
        log.info("sales페이지 호출");
        PageInfo<SalesResponse> salesList = new PageInfo<>(salesService.findAllByPagingSales(pageNum), 10);
        model.addAttribute("salesList", salesList);
        return "admin";
    }

    @GetMapping("/sales/list")
    public @ResponseBody List<SalesResponse> salesList () {
        return salesService.findAllSales();
    }

    @GetMapping("/sales/date")
    public String selectDate(@RequestBody SelectDate selectDate, Model model) {
        log.info("선택한 날짜 : {}", selectDate);
        List<SalesResponse> salesList = salesService.findBySalesDate(selectDate);
        model.addAttribute("salesList", salesList);
        return "admin";
    }
}
