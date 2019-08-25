package com.hao.gomall_core.ui.camera;


import android.net.Uri;

import com.hao.gomall_core.delegates.PermissionCheckedDelegate;
import com.hao.gomall_core.utils.FileUtil;

/**
 * 相机调用类
 */
public class MallCameraa {

    public static Uri createCropFile() {
        return Uri.parse(
                FileUtil.createFile("crop_image", FileUtil.getFileNameByTime("IMG", "jpg"))
                .getPath()
        );
    }

    public static void start(PermissionCheckedDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }

}
