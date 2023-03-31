package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.vo.AdminVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<AdminVo> getAdminList();
    AdminVo findById(String name);
}
