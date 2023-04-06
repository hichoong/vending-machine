package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.dto.request.LoginRequest;
import com.ezace.vendingmachine.domain.vo.AdminVo;
import com.ezace.vendingmachine.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/login")
    public String login(Model model) {
        log.info("login페이지 호출");
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login-admin")
    public String login(@ModelAttribute("loginRequest") LoginRequest loginRequest, HttpSession session, Model model) {
        log.info("로그인 : {}", loginRequest);
        AdminVo loginUser = adminService.login(loginRequest);
        if (loginUser == null) {
            model.addAttribute("error", "아이디와 비밀번호를 확인하세요.");
            return "login";
        }
        log.info("회원 정보 : {}", loginUser);
        session.setAttribute("loginUser", loginUser);
        return "redirect:/sales";
    }
}
