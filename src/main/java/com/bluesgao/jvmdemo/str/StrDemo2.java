package com.bluesgao.jvmdemo.str;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class StrDemo2 {
    public static void main(String[] args) {
        String str1 = "111";
        String a = "111";
        String str2 = "111" + "";
        System.out.println(str1 == str2);//true ?
        /**
         * 直接在表达式里写值，java不用根据变量去内存里找对应的值，可以在编译的时候直接对这个表达式进行优化，
         * 优化后的表达式从 "111"+"" 直接变成了 "111" ，两个String类型的变量都指向了常量池的111字符串，因此结果为true;
         */

        String key="A271086378880491520|内_新消费_黄金流程_推荐公共组（Feed）";
        String[] keys = key.split("\\|");
        System.out.println(keys[0]);
        System.out.println(keys[1]);

        String json = "{\"code\":\"00000000\",\"message\":\"响应成功\",\"result\":{\"id\":10469,\"appKey\":\"83b9146f6e474a42a5f27ebc70f96e1d\",\"appSecret\":\"11ec469fdb1a4cb2891b697be747f33e\",\"userId\":59,\"concurrentAccess\":50,\"whiteList\":null,\"effDate\":1567086824000,\"expDate\":2524579200000,\"createdTime\":null,\"modifiedTime\":null,\"creator\":null,\"modifier\":null,\"appName\":\"jsf_appkey_test_gx\",\"resources\":null,\"status\":1,\"deleted\":false}}";
        JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(json);
        System.out.println(jsonObject.get("code"));
        System.out.println(jsonObject.get("message"));
        System.out.println(jsonObject.get("result"));

        JSONObject resultJson = com.alibaba.fastjson.JSON.parseObject(jsonObject.get("result").toString());
        System.out.println(resultJson.get("appKey"));


    }
}
