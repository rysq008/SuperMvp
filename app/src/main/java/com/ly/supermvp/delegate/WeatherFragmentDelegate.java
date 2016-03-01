package com.ly.supermvp.delegate;

import com.ly.supermvp.R;
import com.ly.supermvp.mvp_frame.view.AppDelegate;

/**
 * <Pre>
 *     天气预报界面视图代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/2/29 17:44
 */
public class WeatherFragmentDelegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_weather;
    }
}
