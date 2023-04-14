package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.vo.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    private static final String title = "충희네 자판기 재고 관련 안내 이메일입니다";
    private static final String message = "안녕하세요. 충희네 자판기에서  안내 메일을 보냅니다."
            +"\n" + "자판기의 상품 재고가 5개 이하가 되었으니 확인해 주시기 바랍니다."
            +"\n" + "재고가 부족한 상품은 다음과 같습니다" + "\n";
    private static final String fromAddress = "ksky8013@gmail.com";

    public EmailMessage createMail(String name) {
        return EmailMessage.builder()
                .to(fromAddress)
                .subject(title)
                .message(message + name)
                .build();

    }

    public void SendMail (EmailMessage emailMessage) {
        log.info("메일 보내기");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailMessage.getTo());
        mailMessage.setSubject(emailMessage.getSubject());
        mailMessage.setText(emailMessage.getMessage());
        mailMessage.setFrom(emailMessage.getTo());
        log.info("메일 정보 : {}", mailMessage);
        mailSender.send(mailMessage);

    }
}
