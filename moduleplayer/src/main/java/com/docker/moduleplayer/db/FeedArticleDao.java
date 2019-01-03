package com.docker.moduleplayer.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.docker.moduleplayer.vo.BannerVo;
import com.docker.moduleplayer.vo.FeedArticleListData;

/**
 * Created by zhangxindang on 2018/12/25.
 */

@Dao
public interface FeedArticleDao {

    @Query("SELECT * FROM FeedArticleListData where curPage =:page")
    LiveData<FeedArticleListData> LoadFeedData(int page);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(FeedArticleListData... feedArticleListData);

    @Delete
    void delete(FeedArticleListData feedArticleListData);

    @Update
    void update(FeedArticleListData feedArticleListData);

//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertBanner(BannerVo... bannerVo);

}
