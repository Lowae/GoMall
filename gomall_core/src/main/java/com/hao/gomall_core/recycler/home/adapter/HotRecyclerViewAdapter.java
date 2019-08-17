package com.hao.gomall_core.recycler.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hao.gomall_core.R;
import com.hao.gomall_core.recycler.home.bean.ResultBeanData;
import com.hao.gomall_core.utils.Constants;

import java.util.List;

public class HotRecyclerViewAdapter extends RecyclerView.Adapter<HotRecyclerViewAdapter.ViewHolder> {

    private  Context mContext;
    private  List<ResultBeanData.ResultBean.HotInfoBean> datas;

    private OnHotRecyclerViewClickListener onHotRecyclerViewClickListener;

    public HotRecyclerViewAdapter(Context context, List<ResultBeanData.ResultBean.HotInfoBean> hot_info){
        mContext =context;
        datas = hot_info;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_hot_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = datas.get(i);
        Glide.with(mContext).load(Constants.BASE_IMAGE_URL + hotInfoBean.getFigure()).optionalCenterCrop().into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotInfoBean.getName());
        viewHolder.tv_price.setText(String.format("￥%s", hotInfoBean.getCover_price()));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_hot;
        private TextView tv_name;
        private TextView tv_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_hot = itemView.findViewById(R.id.iv_hot);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onHotRecyclerViewClickListener != null){
                        onHotRecyclerViewClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnHotRecyclerViewClickListener{
        void onItemClick(int position);
    }

    public void setOnHotRecyclerViewClickListener(OnHotRecyclerViewClickListener onHotRecyclerViewClickListener){
        this.onHotRecyclerViewClickListener = onHotRecyclerViewClickListener;
    }
}
