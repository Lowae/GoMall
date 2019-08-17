package com.hao.gomall_core.recycler.home.bean;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class GoodsBean implements Serializable {

    //价格
    private String cover_price;
    //图片
    private String figure;
    //名称
    private String name;
    //产品id
    private String product_id;

    private int number = 1;

    private boolean isSelected = true;

    public GoodsBean(){

    }

    public GoodsBean(String cover_price, String figure, String name, String product_id) {
        this.cover_price = cover_price;
        this.figure = figure;
        this.name = name;
        this.product_id = product_id;
    }

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @NotNull
    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                ", isSelected=" + isSelected +
                '}';
    }
}
