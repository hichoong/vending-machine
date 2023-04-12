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
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        List<SalesResponse> allSales = salesService.findAllSales();
        int total = allSales.stream().mapToInt(SalesResponse::getPrice).sum();
        model.addAttribute("total", total);
        return "sales";
    }
    @GetMapping("/sales/date")
    public String selectDate(@RequestParam(required = false)String firstChoiceDate, Model model, @RequestParam(required = false, defaultValue = "1")int pageNum) {
        log.info("선택한 날짜 : {}", firstChoiceDate);
        if (firstChoiceDate == null) {
            firstChoiceDate = nowDateFormat();
        }
        log.info("선택한 날짜 : {}", firstChoiceDate);
        PageInfo<SalesResponse> salesList = new PageInfo<>(salesService.findBySalesDate(pageNum, firstChoiceDate), 10);
       log.info("해당 정보 = {}", salesList);
        model.addAttribute("salesList", salesList);
        List<SalesResponse> totalSales = salesService.findBySalesDate(firstChoiceDate);
        int total = totalSales.stream().mapToInt(SalesResponse::getPrice).sum();
        model.addAttribute("total", total);
        model.addAttribute("firstChoiceDate", firstChoiceDate);
        return "sales-date";
    }

    @GetMapping("/sales/statistics")
    public String selectStatistics() {
        log.info("통계 페이지");
        List<SalesResponse> allSales = salesService.findAllSales();
        return "sales-statistics";
    }

    @GetMapping("/sales/goods")
    public String salesGoods(Model model) {
        log.info("품목별 판매량");
        List<SalesResponse> salesList = salesService.findByGoods();
        log.info("품목 = {}", salesList );
        model.addAttribute("salesList", salesList);
        return "sales-goods";
    }

    private String nowDateFormat() {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = simpleDateFormat.format(now);
        return nowDate;
    }

}
