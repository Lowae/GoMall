package com.hao.gomall_core.recycler.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hao.gomall_core.R;
import com.hao.gomall_core.recycler.home.IStartGoodsInfo;
import com.hao.gomall_core.recycler.home.bean.GoodsBean;
import com.hao.gomall_core.recycler.home.bean.ResultBeanData;
import com.hao.gomall_core.utils.Constants;
import com.hao.gomall_core.widget.PullRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.ScaleInOutTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragmentAdapter extends PullRecyclerView.PullRefreshAdapter {

    public static final int BANNER = 0;
    public static final int CHANNEL = 1;
    public static final int ACT = 2;
    public static final int SECKILL = 3;
    public static final int RECOMMEND = 4;
    public static final int HOTHEADER = 5;
    public static final int HOT = 6;

    public static final String GOODS_BEAN = "goodsBean";

    private Context mContext;
    private ResultBeanData.ResultBean resultBean;
    private LayoutInflater layoutInflater;

    public List<ResultBeanData.ResultBean.HotInfoBean> hotInfoBeans;

    private IStartGoodsInfo iStartGoodsInfo;

    //当前类型
    private int currentType = BANNER;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean, IStartGoodsInfo iStartGoodsInfo) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        hotInfoBeans = resultBean.getHot_info();
        layoutInflater = LayoutInflater.from(mContext);
        this.iStartGoodsInfo = iStartGoodsInfo;
    }



    @Override
    protected int getOtherItemType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOTHEADER) {
            currentType = HOTHEADER;
        } else {
            currentType = HOT;
        }
        return currentType;
    }

    @Override
    protected void onBindOtherViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) viewHolder;
            channelViewHolder.setData(resultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) viewHolder;
            actViewHolder.setData(resultBean.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) viewHolder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) viewHolder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) viewHolder;
            hotViewHolder.setData(position - HOT);
        }
    }

    @Override
    protected RecyclerView.ViewHolder onOtherItemHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, layoutInflater.inflate(R.layout.banner_viewpager, viewGroup, false));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, layoutInflater.inflate(R.layout.channel_item, viewGroup, false));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, layoutInflater.inflate(R.layout.act_item, viewGroup, false));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, layoutInflater.inflate(R.layout.seckill_item,null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, layoutInflater.inflate(R.layout.recommend_item, viewGroup,false));
        } else if (viewType == HOTHEADER) {
            return new HotHeaderHolder(layoutInflater.inflate(R.layout.hot_item, viewGroup, false));
        } else if (viewType == HOT) {
            return new HotViewHolder(layoutInflater.inflate(R.layout.item_hot_recyclerview, null));
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return hotInfoBeans.size() + HOT + 1;
    }


    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        iStartGoodsInfo.startGoodsInfo(goodsBean);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private View itemView;
        private Banner banner;

        BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.itemView = itemView;
            this.banner = itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            List<String> imagesUrl = new ArrayList<>();
            for (ResultBeanData.ResultBean.BannerInfoBean bannerInfoBean : banner_info) {
                imagesUrl.add(bannerInfoBean.getImage());
                Log.e("TAG", bannerInfoBean.getImage());
            }
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(Constants.BASE_IMAGE_URL + path).into(imageView);
                }
            }).start();
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position = " + position, Toast.LENGTH_SHORT).show();
//                    startGoodsInfoActivity();
                }
            });
        }
    }


    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private GridView channel_gridView;

        ChannelViewHolder(final Context mContext, @NonNull View itemView) {
            super(itemView);
            this.mContext = mContext;
            channel_gridView = itemView.findViewById(R.id.gv_channel);

        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            ChannelAdapter adapter = new ChannelAdapter(mContext, channel_info);
            channel_gridView.setAdapter(adapter);
            channel_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position <= 8) {
                        Toast.makeText(mContext, "position : " + position, Toast.LENGTH_SHORT).show();
                        /*Intent intent = new Intent(mContext, GoodsListActivity.class);
                        intent.putExtra("position", position);
                        mContext.startActivity(intent);*/
                    }
                }
            });
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {

            act_viewpager.setPageMargin(20);
            act_viewpager.setOffscreenPageLimit(3);
            act_viewpager.setPageTransformer(true, new ScaleInOutTransformer());
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                    return view == o;
                }

                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    Glide.with(mContext).load(Constants.BASE_IMAGE_URL + act_info.get(position).getIcon_url()).into(imageView);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                        }
                    });

                    container.addView(imageView);
                    return imageView;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;

        //相差多少时间-毫秒
        private long dt = 0;

        private Handler handler = new Handler();
        private Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dt = dt - 1000;
                SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getTimeInstance();
                String time = format.format(new Date(dt));
                tv_time_seckill.setText(time);
                handler.removeCallbacks(runnable);
                if (dt <= 0) {
                    handler.removeCallbacksAndMessages(null);
                } else {
                    handler.postDelayed(runnable, 1000);
                }
            }
        };

        public SeckillViewHolder(Context mContext, @NonNull View itemView) {
            super(itemView);
            tv_time_seckill = itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = itemView.findViewById(R.id.rv_seckill);
            this.mContext = mContext;
        }

        public void setData(final ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            SeckillRecyclerViewAdapter adapter = new SeckillRecyclerViewAdapter(mContext, seckill_info.getList());
            adapter.setOnSeckillRecyclerViewClickListener(new SeckillRecyclerViewAdapter.OnSeckillRecyclerViewClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, "秒杀 = " + position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = seckill_info.getList().get(position);

                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(listBean.getCover_price());
                    goodsBean.setFigure(listBean.getFigure());
                    goodsBean.setName(listBean.getName());
                    goodsBean.setProduct_id(listBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });
            rv_seckill.setAdapter(adapter);

            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

            //秒杀倒计时计算
            dt = Long.parseLong(seckill_info.getEnd_time()) - Long.parseLong(seckill_info.getStart_time());

            //handler.sendEmptyMessageDelayed(0, 1000);
            handler.postDelayed(runnable, 1000);
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private GridView gv_recommend;

        public RecommendViewHolder(Context mContext, @NonNull View itemView) {
            super(itemView);
            context = mContext;
            gv_recommend = itemView.findViewById(R.id.gv_recommend);
        }

        public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            RecommendGridViewAdapter adapter = new RecommendGridViewAdapter(context, recommend_info);
            gv_recommend.setAdapter(adapter);

            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);

                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    class HotHeaderHolder extends RecyclerView.ViewHolder {

        public HotHeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_hot;
        private TextView tv_name;
        private TextView tv_price;
        private CardView hot_view;

        public HotViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_hot = itemView.findViewById(R.id.iv_hot);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            hot_view = itemView.findViewById(R.id.hot_view);
        }

        public void setData(int position) {
            ResultBeanData.ResultBean.HotInfoBean hotInfoBean = hotInfoBeans.get(position);
            Glide.with(mContext).load(Constants.BASE_IMAGE_URL + hotInfoBean.getFigure()).optionalCenterCrop().into(iv_hot);
            tv_name.setText(hotInfoBean.getName());

            tv_price.setText(String.format("￥%s", hotInfoBean.getCover_price()));
            hot_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "position = " + position, Toast.LENGTH_SHORT).show();
                    //商品信息类
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }
}
