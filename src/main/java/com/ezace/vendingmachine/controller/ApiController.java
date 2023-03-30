package com.ezace.vendingmachine.controller;

import com.ezace.vendingmachine.domain.Admin;
import com.ezace.vendingmachine.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiController {
    private final AdminService adminService;

    @GetMapping("/list")
    public List<Admin> adminList() {
        return adminService.getAdminList();
    }
}
