package com.jinjiang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jinjiang.mapper.JinjiangMapper;
import com.jinjiang.pojo.Jinjiang;
import com.jinjiang.service.JinjiangService;
import com.jinjiang.util.HttpclientUtil;
import com.jinjiang.util.ServerResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @description:
 * @author: xiangchao
 * @date: 2019/12/21 4:11 下午
 */
@Service
public class JinjiangSerivceImpl implements JinjiangService {

    @Autowired
    private JinjiangMapper jinjiangMapper;

    @Override
    public HashMap<String, Object> saveJinJiangfun(String orderCode) throws Exception {
        String result = HttpclientUtil.postJsonParams("http://partnermeb.bestwehotel.com/member-open/rest/oauth/partnerExchange","{\"partnerCode\":\"YOUWAN\",\"phoneNumber\":\"13688855888\",\"orderCode\":\""+orderCode+"\",\"exchPoint\":\"\",\n" +
                "\"idcard\":\"150269199905054598\",\"giftCode\":1,\"rulePlan\":\"\"}");
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println("======="+result);
        String ordercode = jsonObject.getString("orderCode");
        String cardno = jsonObject.getString("cardNo");
        Jinjiang jinjiang = new Jinjiang();
        jinjiang.setOrderCode(ordercode);
        jinjiang.setCardNo(cardno);
        int i = jinjiangMapper.saveJinJiang(jinjiang);
        if(i > 0){
            return ServerResponseUtil.successful();
        } else {
            return ServerResponseUtil.successFailed();
        }
    }
}
