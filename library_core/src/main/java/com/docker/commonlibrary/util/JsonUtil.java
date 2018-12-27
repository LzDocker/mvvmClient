package com.docker.commonlibrary.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * Created by zhangxindang on 2018/12/26.
 */

public class JsonUtil {


    /**
     * List<T> 转 json 保存到数据库
     */
    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }



}
