package com.docker.moduleplayer.db;

import android.arch.persistence.room.TypeConverter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.docker.commonlibrary.util.JsonUtil;
import com.docker.moduleplayer.vo.FeedArticleData;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by zhangxindang on 2018/12/26.
 */

public class ArticleConverter {
    @TypeConverter
    public static List<FeedArticleData> revert(String feedArticleStr) {
        // 使用Gson方法把json格式的string转成List
        try {
            return JsonUtil.jsonToList(feedArticleStr, FeedArticleData.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String converter(List<FeedArticleData> feedArticleDataList) {
        // 使用Gson方法把List转成json格式的string，便于我们用的解析
        return new Gson().toJson(feedArticleDataList);
    }


}
