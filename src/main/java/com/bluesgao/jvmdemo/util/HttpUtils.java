package com.bluesgao.jvmdemo.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtils {
    public static String get(String url) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionTimeToLive(100, TimeUnit.MILLISECONDS).build();
        HttpGet httpGet = new HttpGet(url.trim());
        String responseStr = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseStr = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseStr;
    }

    public static String post(String url, String contentType, Map<String, String> params, Map<String, String> cookies) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionTimeToLive(100, TimeUnit.MILLISECONDS).build();

        //设置url
        HttpPost httpPost = new HttpPost(url.trim());

        //设置content-type
        if (contentType == null || contentType.length() <= 0) {
            httpPost.setHeader("Content-Type", " application/x-www-form-urlencoded;charset=UTF-8");
        } else {
            httpPost.setHeader("Content-Type", contentType);
        }

        //设置post参数
        if (params != null && params.size() > 0) {
            List<BasicNameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null && value != null) {
                    list.add(new BasicNameValuePair(key, value));
                }
            }
            if (list != null && list.size() > 0) {
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        //设置cookies
        if (cookies != null && cookies.size() > 0) {
            BasicCookieStore cookieStore = new BasicCookieStore();
            for (Map.Entry<String, String> entry : cookies.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null && value != null) {
                    BasicClientCookie cookie = new BasicClientCookie(key, value);
                    cookieStore.addCookie(cookie);
                }
            }
            httpPost.addHeader("Cookie", cookieStore.toString());
        }

        String responseStr = null;

        //执行post请求
        try {
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseStr = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseStr;
    }

    public static void main(String[] args) {
        System.out.println(HttpUtils.get("https://www.baidu.com"));
    }
}
