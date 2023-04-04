package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.dto.request.LoginRequest;
import com.ezace.vendingmachine.domain.vo.AdminVo;
import com.ezace.vendingmachine.domain.vo.GoodsVo;
import com.ezace.vendingmachine.domain.vo.SalesVo;
import com.ezace.vendingmachine.service.AdminService;
import com.ezace.vendingmachine.service.GoodsService;
import com.ezace.vendingmachine.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexContoller {
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

    @GetMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest) {
        log.info("login페이지 호출");
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginRequest loginRequest, Model model) {
        log.info("로그인 : {}", loginRequest);
        AdminVo loginUser = adminService.Login(loginRequest);
        if (loginUser == null) {
            return "login";
        }
        log.info("회원 정보 : {}", loginUser);
        model.addAttribute("loginUser", loginUser);
        return "admin";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        log.info("admin페이지 호출");
        List<SalesVo> allSales = salesService.findAllSales();
        model.addAttribute("salesList", allSales);
        return "admin";
    }
}
