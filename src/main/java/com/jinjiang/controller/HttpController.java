package com.jinjiang.controller;

import com.jinjiang.util.HttpclientUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: xiangchao
 * @date: 2019/12/19 5:53 下午
 */
@RestController
public class HttpController {

    @RequestMapping("postJsonParams")
    public String postJsonParams(String orderCode){
        try {
            return HttpclientUtil.postJsonParams("http://partnermeb.bestwehotel.com/member-open/rest/oauth/partnerExchange","{\"partnerCode\":\"YOUWAN\",\"phoneNumber\":\"13688855888\",\"orderCode\":\""+orderCode+"\",\"exchPoint\":\"\",\n" +
                    "\"idcard\":\"150269199905054598\",\"giftCode\":1,\"rulePlan\":\"\"}");
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
