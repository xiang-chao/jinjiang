package com.jinjiang.controller;

import com.jinjiang.service.JinjiangService;
import com.jinjiang.util.HttpclientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @description: 调用锦江接口拿礼包（金卡）
 * @author: xiangchao
 * @date: 2019/12/19 5:53 下午
 */
@RestController
public class HttpController {

    @Autowired
    private JinjiangService jinjiangService;

    @PostMapping("postJsonParams")
    public HashMap<String, Object> postJsonParams(@RequestBody String orderCode){
        try {
            return jinjiangService.saveJinJiangfun(orderCode);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
