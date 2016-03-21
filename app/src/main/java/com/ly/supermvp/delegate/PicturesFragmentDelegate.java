package com.ly.supermvp.delegate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ly.supermvp.R;
import com.ly.supermvp.mvp_frame.view.AppDelegate;

import butterknife.Bind;

/**
 * <Pre>
 *     美图大全视图代理类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 14:52
 */
public class PicturesFragmentDelegate extends AppDelegate{
    @Bind(R.id.list_swipe_refresh)
    SwipeRefreshLayout list_swipe_refresh;
    @Bind(R.id.rv_pictures)
    RecyclerView rv_picture;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_pictures;
    }
}
