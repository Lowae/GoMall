package com.hao.gomall_core.ui.camera;


import android.net.Uri;

/**
 * 存储照片数据
 */
public final class CameraBean {

    private Uri mUri = null;

    private static final CameraBean INSTANCE = new CameraBean();

    public static CameraBean getINSTANCE() {
        return INSTANCE;
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri mUri) {
        this.mUri = mUri;
    }
}
