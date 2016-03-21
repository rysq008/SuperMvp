package com.ly.supermvp.view.fragment;

import com.ly.supermvp.delegate.PicturesFragmentDelegate;
import com.ly.supermvp.mvp_frame.presenter.FragmentPresenter;

/**
 * <Pre>
 *     美图大全fragment
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 14:39
 */
public class PicturesFragment extends FragmentPresenter{
    @Override
    protected Class getDelegateClass() {
        return PicturesFragmentDelegate.class;
    }
}
