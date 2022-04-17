package com.ly.supermvp.model.news;

import com.ly.supermvp.common.BizInterface;
import com.ly.supermvp.model.entity.ShowApiNews;
import com.ly.supermvp.model.entity.ShowApiResponse;
import com.ly.supermvp.server.RetrofitService;

import retrofit2.Call;


/**
 * <Pre>
 * 新闻数据实现类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/28 14:54
 */
public class NewsModelImpl implements NewsModel {
    public static final String CHANNEL_ID = "5572a109b3cdc86cf39001db";//频道id 来自api指定
    public static final String CHANNEL_NAME = "国内最新";//频道名称 来自api指定

    @Override
    public Call<ShowApiResponse<ShowApiNews>> netLoadNewsList(int page, String channelId, String channelName) {
        //注意，此处采用Retrofit的官方响应方式，天气预报采用RxJava，学习一下两种用法
        Call<ShowApiResponse<ShowApiNews>> call = RetrofitService.getInstance()
                .createAPI()
                .getNewsList(RetrofitService.getCacheControl(), BizInterface.SHOW_API_APPID,
                        BizInterface.SHOW_API_KEY, page, channelId, channelName);

        return call;

        /*call.enqueue(new Callback<ShowApiResponse<ShowApiNews>>() {
            @Override
            public void onResponse(Call<ShowApiResponse<ShowApiNews>> call, Response<ShowApiResponse<ShowApiNews>> response) {
                Logger.d(response.message() + response.code() + response.body().showapi_res_code
                        + response.body().showapi_res_error);
                if (response.body() != null && TextUtils.equals("0", response.body().showapi_res_code)) {
                    listListener.onSuccess(response.body().showapi_res_body.pagebean.contentlist);
                } else {
                    listListener.onFailure(new Exception());
                }
            }

            @Override
            public void onFailure(Call<ShowApiResponse<ShowApiNews>> call, Throwable t) {
                listListener.onFailure(t);
            }
        });*/
    }
}
