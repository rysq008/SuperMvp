package com.ly.supermvp.view.fragment;

import com.ly.supermvp.adapter.PictureGridAdapter;
import com.ly.supermvp.delegate.PicturesFragmentDelegate;
import com.ly.supermvp.delegate.SwipeRefreshAndLoadMoreCallBack;
import com.ly.supermvp.model.OnNetRequestListener;
import com.ly.supermvp.model.entity.pictures.PictureBody;
import com.ly.supermvp.model.entity.pictures.PicturesModel;
import com.ly.supermvp.model.entity.pictures.PicturesModelImpl;
import com.ly.supermvp.mvp_frame.presenter.FragmentPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * <Pre>
 * 美图大全fragment
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 14:39
 */
public class PicturesFragment extends FragmentPresenter<PicturesFragmentDelegate> implements SwipeRefreshAndLoadMoreCallBack {
    private PicturesModel mPicturesModel;
    private PictureGridAdapter mPictureGridAdapter;

    private int mPageNum = 1;

    private List<PictureBody> mList = new ArrayList<>();

    public static PicturesFragment newInstance() {
        PicturesFragment fragment = new PicturesFragment();
        return fragment;
    }

    @Override
    protected Class<PicturesFragmentDelegate> getDelegateClass() {
        return PicturesFragmentDelegate.class;
    }

    @Override
    protected void initData() {
        super.initData();
        mPicturesModel = new PicturesModelImpl();
        mPictureGridAdapter = new PictureGridAdapter(mList, getActivity());

        viewDelegate.setListAdapter(mPictureGridAdapter);

        //注册下拉刷新
        viewDelegate.registerSwipeRefreshCallBack(this);
        //注册加载更多
        viewDelegate.registerLoadMoreCallBack(this, mPictureGridAdapter);

        netLoadPictures(true);
    }

    /**
     * 网络获取图片
     */
    private void netLoadPictures(final boolean isRefresh) {
        if (isRefresh) {
            mPageNum = 1;
        } else {
            mPageNum++;
        }
        mPicturesModel.netLoadPictures(PicturesModel.ARG_TYPE, mPageNum, new OnNetRequestListener<List<PictureBody>>() {
            @Override
            public void onStart() {
                viewDelegate.showRefreshLayout();
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(List<PictureBody> data) {
                viewDelegate.showContent();
                if (isRefresh) {
                    if (!mList.isEmpty()) {
                        mList.clear();
                    }
                }
                mList.addAll(data);
                mPictureGridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void refresh() {
        netLoadPictures(true);
    }

    @Override
    public void loadMore() {
        netLoadPictures(false);
    }
}
