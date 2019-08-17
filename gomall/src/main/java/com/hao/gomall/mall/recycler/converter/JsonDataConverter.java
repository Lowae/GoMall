package com.hao.gomall.mall.recycler.converter;

import java.util.ArrayList;

public abstract class JsonDataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData;

    public abstract ArrayList<MultipleItemEntity> convert();


    public JsonDataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

    public void clearData(){
        ENTITIES.clear();
    }
}
