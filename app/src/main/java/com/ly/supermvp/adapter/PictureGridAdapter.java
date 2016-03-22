package com.ly.supermvp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ly.supermvp.R;
import com.ly.supermvp.model.entity.pictures.PictureBody;
import com.ly.supermvp.utils.GlideUtil;
import com.ly.supermvp.widget.RatioImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <Pre>
 *     美图大全瀑布流适配器
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 16:36
 */
public class PictureGridAdapter extends RecyclerView.Adapter{
    private List<PictureBody> mList;
    private Activity context;

    public PictureGridAdapter(List<PictureBody> mList, Activity context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_grid, parent,false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            PictureBody pictureBody = mList.get(position);
            GlideUtil.loadImage(context, pictureBody.list.get(0).middle, viewHolder.iv_picture);
            viewHolder.tv_title.setText(pictureBody.title);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_picture)
        RatioImageView iv_picture;
        @Bind(R.id.tv_title)
        TextView tv_title;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            iv_picture.setOriginalSize(50, 50);
        }
    }
}
