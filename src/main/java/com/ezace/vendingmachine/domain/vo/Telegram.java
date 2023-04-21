package com.ezace.vendingmachine.domain.vo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Telegram {
    public static void sendTelegramMessage(String goods) {
        final String  token = "6015598495:AAE9QKO0i2MDw4piq2o1Q5fvclBvODGPSDY";
        final String chat_id = "6133591735";
        final String text = "자판기 상품 재고가 5개 이하가 되었습니다. 부족한 재고는 다음과 같습니다. ";

        BufferedReader in = null;

        try {
            URL obj = new URL("https://api.telegram.org/bot" + token + "/sendmessage?chat_id=" + chat_id + "&text=" + text + goods); // 호출할 url

            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line;

            while((line = in.readLine()) != null) { // response를 차례대로 출력
                System.out.println(line);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
        }
    }
}
