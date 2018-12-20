package com.docker.moduleplayer.api;

import android.arch.lifecycle.LiveData;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.moduleplayer.vo.BannerVo;
import com.docker.moduleplayer.vo.FeedArticleListData;
import com.docker.moduleplayer.vo.KnowledgeHierarchyData;
import com.docker.moduleplayer.vo.NavigationListData;
import com.docker.moduleplayer.vo.ProjectClassifyData;
import com.docker.moduleplayer.vo.ProjectListData;
import com.docker.moduleplayer.vo.WxAuthor;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhangxindang on 2018/12/13.
 */

public interface PlayerService {


    @GET("banner/json")
    LiveData<ApiResponse<BaseResponse<List<BannerVo>>>> getBanner();

    /**
     * 获取feed文章列表
     *
     * @param num 页数
     * @return feed文章列表数据
     */
    @GET("article/list/{num}/json")
    LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> getIndexList(@Path("num") int num);


    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     *
     * @return 知识体系数据
     */
    @GET("tree/json")
    LiveData<ApiResponse<BaseResponse<List<KnowledgeHierarchyData>>>> getKnowledgeHierarchyData();


    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0?cid=60
     *
     * @param page page num
     * @param cid  second page id
     * @return 知识体系feed文章数据
     */
    @GET("article/list/{page}/json")
    LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> getKnowledgeHierarchyDetailData(@Path("page") int page, @Query("cid") int cid);


    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号列表数据
     */
    @GET("wxarticle/chapters/json")
    LiveData<ApiResponse<BaseResponse<List<WxAuthor>>>> getWxAuthorListData();


    /**
     * 获取当前公众号某页的数据
     * http://wanandroid.com/wxarticle/list/405/1/json
     *
     * @param id
     * @param page
     * @return 获取当前公众号某页的数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> getWxSumData(@Path("id") int id, @Path("page") int page);

    /**
     * 指定搜索内容，搜索当前公众号的某页的此类数据
     * http://wanandroid.com/wxarticle/list/405/1/json?k=Java
     *
     * @param id
     * @param page
     * @param k
     * @return 指定搜索内容，搜索当前公众号的某页的此类数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    LiveData<ApiResponse<BaseResponse<FeedArticleListData>>> getWxSearchSumData(@Path("id") int id, @Path("page") int page, @Query("k") String k);


    /**
     * 导航
     * http://www.wanandroid.com/navi/json
     *
     * @return 导航数据
     */
    @GET("navi/json")
    LiveData<ApiResponse<BaseResponse<List<NavigationListData>>>> getNavigationListData();


    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     *
     * @return 项目分类数据
     */
    @GET("project/tree/json")
    LiveData<ApiResponse<BaseResponse<List<ProjectClassifyData>>>> getProjectClassifyData();

    /**
     * 项目类别数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid  second page id
     * @return 项目类别数据
     */
    @GET("project/list/{page}/json")
    LiveData<ApiResponse<BaseResponse<ProjectListData>>>getProjectListData(@Path("page") int page, @Query("cid") int cid);

}
