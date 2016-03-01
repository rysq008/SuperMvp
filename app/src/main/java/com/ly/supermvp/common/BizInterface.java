package com.ly.supermvp.common;

/**
 * <Pre>
 *     网络请求相关配置
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 15:24
 */
public interface BizInterface {
    /**
     * 百度API接口
     */
    String API = "http://apis.baidu.com";
    /**
     * 开发者API密钥
     */
    String API_KEY = "4720bdbcfb3aa457eefd38d2f8fa580f";
    /**
     * 新闻接口
     服务商： 易源接口
     */
    String NEWS_URL = "/showapi_open_bus/channel_news/search_news";
    /**
     * 天气预报 (根据地名)
     服务商： 易源接口
     */
    String WEATHER_URL = "/showapi_open_bus/weather_showapi/address";

}
