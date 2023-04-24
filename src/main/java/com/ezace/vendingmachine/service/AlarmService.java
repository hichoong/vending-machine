package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.vo.Telegram;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final EmailService emailService;


    @EventListener
    public void sendEmailAndTelegram(String goodsName) {
        Telegram.sendTelegramMessage(goodsName);
        emailService.SendMail(goodsName);
    }
}
