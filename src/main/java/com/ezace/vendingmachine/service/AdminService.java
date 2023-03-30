package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.Admin;
import com.ezace.vendingmachine.repository.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    public List<Admin> getAdminList() {
        return adminMapper.getAdminList();
    }
}
