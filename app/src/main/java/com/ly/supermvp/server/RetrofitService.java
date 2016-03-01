package com.ly.supermvp.server;

import com.ly.supermvp.common.BizInterface;
import com.ly.supermvp.server.api.ShowApi;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * <Pre>
 *     网络请求引擎类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 15:14
 */
public class RetrofitService {

    private RetrofitService(){}

    private volatile static RetrofitService instance = null;

    public static RetrofitService getInstance(){
        if(instance == null){
            synchronized (RetrofitService.class){
                if(instance == null){
                    instance = new RetrofitService();
                }
            }
        }
        return instance;
    }

    private volatile static ShowApi showApi = null;
    public static ShowApi createNewsApi(){
        if(showApi == null){
            synchronized (RetrofitService.class){
                if(showApi == null){
                    showApi = new Retrofit.Builder()
                            .baseUrl(BizInterface.API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build().create(ShowApi.class);
                }
            }
        }
        return showApi;
    }

}
