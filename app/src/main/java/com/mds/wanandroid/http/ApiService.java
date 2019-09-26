package com.mds.wanandroid.http;

import com.mds.wanandroid.bean.CurrencyBean;
import com.mds.wanandroid.ui.information.bean.BannerBean;
import com.mds.wanandroid.ui.information.bean.MainListBean;
import com.mds.wanandroid.ui.information.bean.MyCollectBean;
import com.mds.wanandroid.ui.information.bean.ProjectListBean;
import com.mds.wanandroid.ui.information.bean.ProjectTitleBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 20:41
 */
public interface ApiService {

    String BASE_URL = "https://www.wanandroid.com/";

    //网络请求时长
    int HTTP_TIME =30;

    /***
     * 登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponse<CurrencyBean.DataBean>> login(@Field("username") String username, @Field("password") String password);


    /***
     * 登出
     * @return
     */
    @GET("user/logout/json")
    Observable<BaseResponse<CurrencyBean.DataBean>> loginOut();


    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseResponse<CurrencyBean.DataBean>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


    /***
     * 获取首页列表数据
     * @param page
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<MainListBean.DataBean>> getHomeList(@Path("page") int page);

    /***
     * 首页banner
     * @return
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerBean.DataBean>>> getBanner();

    /****
     * 站内收藏
     * @param id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<CurrencyBean.DataBean>> collectIn(@Path("id") String id);
    /****
     * 取消收藏
     * @param id
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse<CurrencyBean.DataBean>> cancelCollect(@Path("id") String id);

    /***
     * 我的收藏
     * @return
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<MyCollectBean.DataBean>> getMyCollect(@Path("page") int page);

    /***
     * 获取项目模块的title
     * @return
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectTitleBean.DataBean>>> getProjectTitle();

    /***
     * 获取项目模块的内容
     * @param page
     * @param id
     * @return
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ProjectListBean.DataBean>> getProjectList(@Path("page") int page, @Query("cid") String id);
}
