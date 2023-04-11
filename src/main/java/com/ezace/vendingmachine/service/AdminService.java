package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.Role;
import com.ezace.vendingmachine.domain.dto.request.JoinForm;
import com.ezace.vendingmachine.domain.dto.request.LoginRequest;
import com.ezace.vendingmachine.domain.vo.AdminVo;
import com.ezace.vendingmachine.repository.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminService {
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    public List<AdminVo> getAdminList() {
        return adminMapper.findAll();
    }

    public Model login(LoginRequest loginRequest, Model model) {
        AdminVo admin = adminMapper.findByName(loginRequest.getName());
        if (admin == null) {
            model.addAttribute("msg1", "아이디를 확인해 주세요.");
            return model;
        }
        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword());
        if (!matches) {
            model.addAttribute("msg2", "비밀번호가 일치하지 않습니다.");
            return model;
        }
        model.addAttribute("admin", admin);
        return model;
    }

    public Model createAdmin(JoinForm joinForm, Model model) {
        if (nameCheck(joinForm.getName()) != null) {
             model.addAttribute("msg1", "이미 해당 이름이 존재합니다.");
             return model;
        }
        if (!joinForm.getPassword().equals(joinForm.getPassword2())) {
             model.addAttribute("msg2", "비밀번호가 일치하지 않습니다.");
            return model;
        }
        AdminVo adminVo = new AdminVo();
        adminVo.setName(joinForm.getName());
        adminVo.setPassword(passwordEncoder.encode(joinForm.getPassword())); //암호화 진행
        adminVo.setRole(String.valueOf(Role.ADMIN));
        adminMapper.insertAdmin(adminVo);
        log.info("{}님 회원가입 완료!!", adminVo.getName());
        model.addAttribute("admin", adminVo);
        return model;
    }

    private AdminVo nameCheck(String name) {
        return adminMapper.findByName(name);
    }
}
