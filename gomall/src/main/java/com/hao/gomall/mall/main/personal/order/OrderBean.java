package com.hao.gomall.mall.main.personal.order;

import java.util.List;

public class OrderBean {

    /**
     * code : 0
     * message : OK
     * data : [{"id":1,"thumb":"/1478489000522.png","title":"启蒙樂高积木拼装玩具","desc":"启蒙樂高积木拼装玩具益智男童小颗粒智力拼图男孩10岁12儿童礼物","price":12.45,"count":2},{"id":2,"thumb":"/1478489000522.png","title":"玩具","price":10,"desc":"小黄人玩具礼物","count":3},{"id":3,"thumb":"/1478489000522.png","title":"遮阳伞","price":28,"desc":"【古风原创】 自动直柄伞 晴雨伞 【青竹词】包邮  新增折叠伞","count":3}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * thumb : /1478489000522.png
         * title : 启蒙樂高积木拼装玩具
         * desc : 启蒙樂高积木拼装玩具益智男童小颗粒智力拼图男孩10岁12儿童礼物
         * price : 12.45
         * count : 2
         */

        private int id;
        private String thumb;
        private String title;
        private String desc;
        private double price;
        private int count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
