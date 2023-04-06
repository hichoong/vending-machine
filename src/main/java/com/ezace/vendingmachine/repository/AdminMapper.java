package com.ezace.vendingmachine.repository;

import com.ezace.vendingmachine.domain.dto.request.LoginRequest;
import com.ezace.vendingmachine.domain.vo.AdminVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Mapper
@Repository
public interface AdminMapper {
    List<AdminVo> findAll();
    AdminVo findByNameAndPassword(LoginRequest loginRequest);
}
