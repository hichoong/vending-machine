package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.dto.request.LoginRequest;
import com.ezace.vendingmachine.domain.vo.AdminVo;
import com.ezace.vendingmachine.repository.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    public List<AdminVo> getAdminList() {
        return adminMapper.findAll();
    }

    public AdminVo Login(LoginRequest loginRequest) {
        return adminMapper.findByNameAndPassword(loginRequest);
    }
}
