package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.vo.AdminVo;
import com.ezace.vendingmachine.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
public class AdminApiController {
    private final AdminService adminService;

    @GetMapping("/list")
    public List<AdminVo> adminList() {
        return adminService.getAdminList();
    }

    @GetMapping("/login")
    public List<AdminVo> login() {
        return adminService.getAdminList();
    }

}
