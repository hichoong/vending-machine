package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.dto.request.LoginRequest;
import com.ezace.vendingmachine.domain.vo.AdminVo;
import com.ezace.vendingmachine.repository.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final AdminMapper adminMapper;

    public List<AdminVo> getAdminList() {
        return adminMapper.findAll();
    }

    public AdminVo login(LoginRequest loginRequest) {
        return adminMapper.findByNameAndPassword(loginRequest);
    }
}
