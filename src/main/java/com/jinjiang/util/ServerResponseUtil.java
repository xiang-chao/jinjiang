package com.jinjiang.util;

import java.util.HashMap;

/**
 * 服务器返回数据工具类
 * @author xiangchao
 */
public class ServerResponseUtil {

    /**
     * 成功返回数据
     * @param data
     * @return
     */
    public static HashMap<String, Object> success(Object data) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "查询成功!");
        map.put("code", "200");
        map.put("data", data);
        return map;
    }

    /**
     * 操作成功返回
     * @return
     */
    public static HashMap<String, Object> successful() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "操作成功!");
        map.put("code", "200");
        return map;
    }

    /**
     * 操作失败返回
     * @return
     */
    public static HashMap<String, Object> successFailed() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "操作失败!");
        map.put("code", "203");
        return map;
    }

    /**
     * 暂无数据
     * @return
     */
    public static HashMap<String, Object> noData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "暂无数据!");
        map.put("code", "201");
        return map;
    }

    /**
     * 服务器内部异常
     * @return
     */
    public static HashMap<String, Object> serverException() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "服务器异常!");
        map.put("code", "205");
        return map;
    }

    /**
     * 参数错误
     * @return
     */
    public static HashMap<String, Object> parameterError() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "参数错误!");
        map.put("code", "202");
        return map;
    }

}
