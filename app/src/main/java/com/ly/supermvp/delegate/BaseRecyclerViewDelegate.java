package com.ly.supermvp.delegate;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.ly.supermvp.R;
import com.ly.supermvp.common.Constance;
import com.ly.supermvp.mvp_frame.view.AppDelegate;
import com.ly.supermvp.view.LoadingView;
import com.ly.supermvp.widget.ProgressLayout;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * <Pre>
 *     recycleview通用视图代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/23 13:53
 */
public abstract class BaseRecyclerViewDelegate extends AppDelegate implements LoadingView{
    @Bind(R.id.progress_layout)
    ProgressLayout progress_layout;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_refresh_layout;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    /**
     * 初始化recycleview，必须重写
     */
    abstract void initRecyclerView();

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_base_recyclerview;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initSwipeRefreshLayout() {
        swipe_refresh_layout.setColorSchemeResources(Constance.colors);
    }

    /**
     * 设置下拉刷新接口
     *
     * @param callBack 下拉刷新的回调接口
     */
    public void registerSwipeRefreshCallBack(final SwipeRefreshAndLoadMoreCallBack callBack) {
        RxSwipeRefreshLayout.refreshes(swipe_refresh_layout).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                // TODO: 2016/2/29 调用fragment的方法加载数据，需要解耦(已用接口解决)
                callBack.refresh();
            }
        });
    }

    public void setListAdapter(RecyclerView.Adapter adapter) {
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        progress_layout.showLoading();
    }

    @Override
    public void showContent() {
        RxSwipeRefreshLayout.refreshing(swipe_refresh_layout).call(false);
        if (!progress_layout.isContent()) {
            progress_layout.showContent();
        }
    }

    @Override
    public void showError(int messageId, View.OnClickListener listener) {
        RxSwipeRefreshLayout.refreshing(swipe_refresh_layout).call(false);
        if (!progress_layout.isError()) {
            progress_layout.showError(messageId, listener);
        }
    }

    @Override
    public Context getContext() {
        return null;
    }
}
