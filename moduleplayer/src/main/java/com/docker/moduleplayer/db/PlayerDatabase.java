package com.docker.moduleplayer.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.docker.moduleplayer.vo.FeedArticleData;
import com.docker.moduleplayer.vo.FeedArticleListData;

/**
 * Created by zhangxindang on 2018/12/25.
 */

@Database(entities = {FeedArticleListData.class},version = 1)
//@TypeConverters({ArticleConverter.class})
public abstract class PlayerDatabase extends RoomDatabase {

     public abstract FeedArticleDao feedArticleDao();

}
