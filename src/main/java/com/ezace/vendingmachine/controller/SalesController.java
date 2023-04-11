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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SalesController {

    private final SalesService salesService;

    @GetMapping("/sales")
    public String salesList(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum) {
        log.info("sales페이지 호출");
        PageInfo<SalesResponse> salesList = new PageInfo<>(salesService.findAllByPagingSales(pageNum));
        model.addAttribute("salesList", salesList);
        List<SalesResponse> allSales = salesService.findAllSales();
        int total = allSales.stream().mapToInt(SalesResponse::getPrice).sum();
        model.addAttribute("total", total);
        return "sales";
    }

    @GetMapping("/sales/date")
    public String selectDate(@RequestParam(required = false, defaultValue = "now()")LocalDateTime firstChoiceDate, Model model, @RequestParam(required = false, defaultValue = "1")int pageNum) {
        log.info("선택한 날짜 : {}");
        PageInfo<SalesResponse> salesList = new PageInfo<>(salesService.findBySalesDate(pageNum, firstChoiceDate), 10);
        model.addAttribute("salesList", salesList);
        return "sales-date";
    }

    @GetMapping("/sales/month")
    public String selectMonth() {
        log.info("선택한 월 = {}");
        return "sales-month";
    }

    @GetMapping("/sales/goods")
    public String salesGoods() {
        log.info("품목별 판매량");
        return "sales-goods";
    }
}
