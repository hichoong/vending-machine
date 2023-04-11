package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.dto.request.JoinForm;
import com.ezace.vendingmachine.domain.dto.request.LoginRequest;
import com.ezace.vendingmachine.domain.vo.AdminVo;
import com.ezace.vendingmachine.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
        Model login = adminService.login(loginRequest, model);
        if (login.getAttribute("admin") == null) {
            model.addAttribute("error", "아이디와 비밀번호를 확인하세요.");
            return "login";
        }
        log.info("회원 정보 : {}", model.getAttribute("admin"));
        session.setAttribute("loginUser", model.getAttribute("admin"));
        return "redirect:/sales";
    }

    @GetMapping("/sign")
    public String sign(Model model) {
        log.info("회원가입 페이지 호출");
        model.addAttribute("joinForm", new JoinForm());
        return "sign";
    }

    @PostMapping("/sign")
    public String createAdmin(@Valid JoinForm joinForm, BindingResult bindingResult, Model model) {
        log.info("회원가입 정보 전달받음");
        if (bindingResult.hasErrors()) {
            return "sign";
        }
        log.info("회원정보 = {}", joinForm.toString());
        adminService.createAdmin(joinForm, model);
        if (model.getAttribute("msg1") != null || model.getAttribute("msg2") != null) {
            return "sign";
        }
        return "redirect:/login";
    }
}
