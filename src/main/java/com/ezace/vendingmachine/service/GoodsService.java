package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.dto.request.BuyGoods;
import com.ezace.vendingmachine.domain.dto.response.GoodsResponse;
import com.ezace.vendingmachine.domain.vo.EmailMessage;
import com.ezace.vendingmachine.domain.vo.GoodsVo;
import com.ezace.vendingmachine.domain.vo.Telegram;
import com.ezace.vendingmachine.repository.GoodsMapper;
import com.ezace.vendingmachine.repository.SalesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoodsService {
    private final GoodsMapper goodsMapper;
    private final SalesMapper salesMapper;
    private final EmailService emailService;

    public List<GoodsVo> findAllGoods() {
        return goodsMapper.findAllGoods();
    }

    public void modifyGoods(Long id) {
        goodsMapper.modifyGoods(id);
    }

    public void insertGoods(GoodsVo goodsVo) {
        goodsMapper.insertGoods(goodsVo);
    }

    public GoodsVo findById(Long id) {
       return goodsMapper.findById(id);
    }

    //품목 삭제
    public void deleteGoods(){
      goodsMapper.deleteGoods();
    }

    //구매
    @Transactional
    public GoodsResponse purchase(BuyGoods buyGoods) {
        log.info("구매서비스 실행");
        GoodsResponse goodsResponse = new GoodsResponse();
        GoodsVo goodsVo = goodsMapper.findById(buyGoods.getId());
        if (goodsVo == null) {
             goodsResponse.setMsg("아이템에 대한 정보가 없습니다.");
             return goodsResponse;
        }
        log.info("현재 금액 : {}, 음료수 금액 : {}", buyGoods.getMoney(), goodsVo.getPrice());
        if (goodsVo.getPrice() > buyGoods.getMoney()) {
            goodsResponse.setMsg("돈이 부족합니다.");
            goodsResponse.setCount(goodsVo.getCount());
            goodsResponse.setMoney(buyGoods.getMoney());
            return goodsResponse;
        }
        if(goodsVo.getCount() == 0) {
            goodsResponse.setMsg("재고가 부족해서 구매할 수 없습니다.");
            return goodsResponse;
        }
        log.info("상품정보 : {}", goodsVo);
        goodsMapper.modifyGoods(goodsVo.getId());
        salesMapper.insertSales(goodsVo.getName(),goodsVo.getPrice());
        goodsVo = goodsMapper.findById(buyGoods.getId());
        BigDecimal bigDecimalMoney = new BigDecimal(buyGoods.getMoney());
        BigDecimal bigDecimalPrice = new BigDecimal(goodsVo.getPrice());
        BigDecimal subtract = bigDecimalMoney.subtract(bigDecimalPrice);
        int calculate = subtract.intValue();
        goodsResponse.setName(goodsVo.getName());
        goodsResponse.setCount(goodsVo.getCount());
        goodsResponse.setMoney(calculate);
        goodsResponse.setMsg(goodsResponse.getName() + " 구매가 완료되었습니다.");
        if (goodsVo.getCount() <= 5) {
            EmailMessage mail = emailService.createMail(goodsVo.getName());
            emailService.SendMail(mail);
            Telegram.sendTelegramMessage(goodsVo.getName());
        }
        return goodsResponse;
    }
}
