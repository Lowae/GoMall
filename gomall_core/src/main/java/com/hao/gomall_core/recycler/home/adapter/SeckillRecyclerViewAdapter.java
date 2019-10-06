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

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {

    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;
    private Context context;

    private OnSeckillRecyclerViewClickListener onSeckillRecyclerViewClickListener;

    public SeckillRecyclerViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> seckillInfoList) {
        context = mContext;
        list = seckillInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_seckill, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = list.get(i);
        Glide.with(context).load(Constants.BASE_IMAGE_URL + listBean.getFigure()).into(viewHolder.iv_figure);
        viewHolder.tv_cover_price.setText("￥" + listBean.getCover_price());
        viewHolder.tv_origin_price.setText("￥" + listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "秒杀 = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    if (onSeckillRecyclerViewClickListener != null) {
                        onSeckillRecyclerViewClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnSeckillRecyclerViewClickListener {
        void onItemClick(int position);
    }

    public void setOnSeckillRecyclerViewClickListener(OnSeckillRecyclerViewClickListener onSeckillRecyclerViewClickListener) {
        this.onSeckillRecyclerViewClickListener = onSeckillRecyclerViewClickListener;
    }
}
