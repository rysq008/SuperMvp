package com.ly.supermvp.ui.fragment;

import com.ly.supermvp.model.WeatherModel;
import com.ly.supermvp.model.WeatherModelImpl;
import com.ly.supermvp.model.entity.ShowApiWeather;
import com.ly.supermvp.mvp_frame.presenter.FragmentPresenter;
import com.ly.supermvp.delegate.WeatherFragmentDelegate;
import com.orhanobut.logger.Logger;

/**
 * <Pre>
 *     天气预报fragment
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/2/29 17:43
 */
public class WeatherFragment extends FragmentPresenter<WeatherFragmentDelegate> {

    private WeatherModel mWeatherModel;

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    protected Class<WeatherFragmentDelegate> getDelegateClass() {
        return WeatherFragmentDelegate.class;
    }

    @Override
    protected void initData() {
        super.initData();
        mWeatherModel = new WeatherModelImpl();
        netWeather();
    }

    /**
     * 获取天气预报
     */
    private void netWeather() {
        mWeatherModel.netLoadWeatherWithLocation("北京", "1", "1", "1", "1", new WeatherModel.OnLoadWeatherListener() {
            @Override
            public void onSuccess(ShowApiWeather weather) {
                Logger.d(weather.f1.toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
