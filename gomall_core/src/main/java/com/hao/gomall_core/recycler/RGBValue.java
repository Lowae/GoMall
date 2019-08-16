package com.hao.gomall_core.recycler;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class RGBValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RGBValue create(int red, int green, int blue){
        return new AutoValue_RGBValue(red, green, blue);
    }

}
