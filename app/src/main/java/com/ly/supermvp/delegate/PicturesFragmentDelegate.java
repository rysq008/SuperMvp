package com.ly.supermvp.delegate;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.bm.library.PhotoView;
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.ly.supermvp.R;
import com.ly.supermvp.adapter.PictureGridAdapter;
import com.ly.supermvp.common.Constance;
import com.ly.supermvp.mvp_frame.view.AppDelegate;
import com.ly.supermvp.utils.GlideUtil;
import com.ly.supermvp.view.LoadingView;
import com.ly.supermvp.widget.ProgressLayout;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * <Pre>
 * 美图大全视图代理类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 14:52
 */
public class PicturesFragmentDelegate extends AppDelegate implements LoadingView {
    private static final int PRELOAD_SIZE = 6;

    private LinearLayout ll_dialog_holder;
    private DialogPlus mDialog;

    @Bind(R.id.list_progress)
    ProgressLayout list_progress;
    @Bind(R.id.list_swipe_refresh)
    SwipeRefreshLayout list_swipe_refresh;
    @Bind(R.id.rv_pictures)
    RecyclerView rv_picture;

    private StaggeredGridLayoutManager mGridViewLayoutManager;//recycleview视图样式管理器

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_pictures;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initRecyclerView() {
        mGridViewLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        rv_picture.setLayoutManager(mGridViewLayoutManager);
    }

    private void initSwipeRefreshLayout() {
        list_swipe_refresh.setColorSchemeResources(Constance.colors);
    }

    /**
     * 设置下拉刷新接口
     *
     * @param callBack 下拉刷新的回调接口
     */
    public void registerSwipeRefreshCallBack(final SwipeRefreshAndLoadMoreCallBack callBack) {
        RxSwipeRefreshLayout.refreshes(list_swipe_refresh).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                callBack.refresh();
            }
        });
    }

    /**
     * 设置加载更多接口
     *
     * @param callBack 加载更多的回调
     */
    public void registerLoadMoreCallBack(final SwipeRefreshAndLoadMoreCallBack callBack, final PictureGridAdapter adapter) {
        rv_picture.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isBottom = mGridViewLayoutManager.findLastCompletelyVisibleItemPositions(
                        new int[2])[1] >=
                        adapter.getItemCount() -
                                PRELOAD_SIZE;
                if (!list_swipe_refresh.isRefreshing() && isBottom) {
                    callBack.loadMore();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    public void setListAdapter(PictureGridAdapter adapter) {
        rv_picture.setAdapter(adapter);
    }

    public void showRefreshLayout() {
        if (!list_swipe_refresh.isRefreshing()) {
            RxSwipeRefreshLayout.refreshing(list_swipe_refresh).call(true);
        }
    }

    @Override
    public void showLoading() {
        list_progress.showLoading();
    }

    @Override
    public void showContent() {
        RxSwipeRefreshLayout.refreshing(list_swipe_refresh).call(false);
        if (!list_progress.isContent()) {
            list_progress.showContent();
        }
    }

    @Override
    public void showError(int messageId, View.OnClickListener listener) {
        RxSwipeRefreshLayout.refreshing(list_swipe_refresh).call(false);
        if (!list_progress.isError()) {
            list_progress.showError(messageId, listener);
        }
    }

    @Override
    public Context getContext() {
        return null;
    }


    public void showDialog(String imgUrl) {
        ll_dialog_holder = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_image_preview, null);
        Holder holder = new ViewHolder(ll_dialog_holder);
        PhotoView photo_view = (PhotoView) holder.getInflatedView().findViewById(R.id.photo_view);
        photo_view.enable();//启动缩放功能
        GlideUtil.loadImage(getActivity(), imgUrl, photo_view);
        showOnlyContentDialog(holder, Gravity.TOP, false);
    }

    /**
     * 仅显示内容的dialog
     *
     * @param holder
     * @param gravity  显示位置（居中，底部，顶部）
     * @param expanded 是否支持展开（有列表时适用）
     */
    private void showOnlyContentDialog(Holder holder, int gravity,
                                       boolean expanded) {
        mDialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(holder)
                .setGravity(gravity)
                .setExpanded(expanded)
                .setCancelable(true)
                .create();
        mDialog.show();
    }
}
