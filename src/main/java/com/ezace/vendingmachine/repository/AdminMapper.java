package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<Admin> getAdminList();
}
