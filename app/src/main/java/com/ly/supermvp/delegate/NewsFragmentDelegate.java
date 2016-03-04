package com.ly.supermvp.delegate;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.ly.supermvp.R;
import com.ly.supermvp.adapter.NewsListAdapter;
import com.ly.supermvp.common.Constance;
import com.ly.supermvp.model.entity.NewsBody;
import com.ly.supermvp.mvp_frame.view.AppDelegate;
import com.ly.supermvp.view.ListView;
import com.ly.supermvp.view.decoration.ListItemDecoration;
import com.ly.supermvp.widget.ProgressLayout;

import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * <Pre>
 *     新闻页面视图代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 14:34
 */
public class NewsFragmentDelegate extends AppDelegate implements ListView<List<NewsBody>>,
        SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.list_progress)
    ProgressLayout mProgressLayout;
    @Bind(R.id.list_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.list_recycler_view)
    RecyclerView mRecyclerView;

    /**
     * 用于加载更多的列表布局管理器
     */
    private LinearLayoutManager mRecycleViewLayoutManager;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(Constance.colors);
        mRecycleViewLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mRecycleViewLayoutManager);
    }

    /**
     * 设置下拉刷新接口
     *
     * @param callBack 下拉刷新的回调接口
     */
    public void registerSwipeRefreshCallBack(final SwipeRefreshAndLoadMoreCallBack callBack) {
        RxSwipeRefreshLayout.refreshes(mSwipeRefreshLayout).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                // TODO: 2016/2/29 调用fragment的方法加载数据，需要解耦(已用接口解决)
                callBack.refresh();
            }
        });
    }

    /**
     * 设置加载更多接口
     *
     * @param callBack 加载更多的回调
     */
    public void registerLoadMoreCallBack(final SwipeRefreshAndLoadMoreCallBack callBack, final NewsListAdapter adapter) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int lastVisibleItem;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mRecycleViewLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()
                        && adapter.isShowFooter()) {
                    //加载更多
                    callBack.loadMore();
                }
            }
        });

    }


    public void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new ListItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
    }

    public void setListAdapter(NewsListAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showList(List<NewsBody> listObservable) {

    }

//    @Override
//    public boolean isContent() {
//        return mProgressLayout.isContent() || mSwipeRefreshLayout.isRefreshing();
//    }

    @Override
    public void showLoading() {
        mProgressLayout.showLoading();
    }

    @Override
    public void showContent() {
        RxSwipeRefreshLayout.refreshing(mSwipeRefreshLayout).call(false);
        if (!mProgressLayout.isContent()) {
            mProgressLayout.showContent();
        }
    }

    @Override
    public void showError(int messageId, View.OnClickListener listener) {
        RxSwipeRefreshLayout.refreshing(mSwipeRefreshLayout).call(false);
        if (!mProgressLayout.isError()) {
            mProgressLayout.showError(messageId, listener);
        }
    }

    @Override
    public Context getContext() {
        return null;
    }

    /**
     *
     */
    @Override
    public void onRefresh() {

    }

    /**
     * 下拉刷新与加载更多接口，用于presenter与view解耦
     */
    public interface SwipeRefreshAndLoadMoreCallBack {
        /**
         * 下拉刷新
         */
        void refresh();

        /**
         * 加载更多
         */
        void loadMore();
    }
}
