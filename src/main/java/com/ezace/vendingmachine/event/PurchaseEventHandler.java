package com.ezace.vendingmachine.event;

import com.ezace.vendingmachine.domain.vo.Telegram;
import com.ezace.vendingmachine.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseEventHandler {

    private final EmailService emailService;

    @Async
    @EventListener
    public void sendMail(PurchaseEvent purchaseEvent) {
        emailService.SendMail(purchaseEvent.getGoodsName());
    }

    @Async
    @EventListener
    public void sendTelegram(PurchaseEvent purchaseEvent) {
        Telegram.sendTelegramMessage(purchaseEvent.getGoodsName());
    }

}
