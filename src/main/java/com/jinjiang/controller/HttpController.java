package com.jinjiang.controller;

import com.jinjiang.pojo.Jinjiang;
import com.jinjiang.service.JinjiangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public HashMap<String, Object> postJsonParams(@RequestBody Jinjiang jinjiang){
        try {
            return jinjiangService.saveJinJiangfun(jinjiang.getOrderCode());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
