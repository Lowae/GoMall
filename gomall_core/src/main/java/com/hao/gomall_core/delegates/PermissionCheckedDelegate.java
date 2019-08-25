package com.hao.gomall_core.delegates;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.hao.gomall_core.ui.camera.CameraBean;
import com.hao.gomall_core.ui.camera.MallCameraa;
import com.hao.gomall_core.ui.camera.RequestCode;
import com.hao.gomall_core.utils.callback.CallbackManager;
import com.hao.gomall_core.utils.callback.CallbackType;
import com.hao.gomall_core.utils.callback.IGlobalCallback;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yalantis.ucrop.UCrop;

import io.reactivex.functions.Consumer;


/**
 * 运行时权限获取
 */
public abstract class PermissionCheckedDelegate extends BaseDelegate{



    public void startCameraWithCheck(){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            MallCameraa.start(PermissionCheckedDelegate.this);
                        }else {
                            ToastUtils.showShort("权限被拒绝");
                        }
                    }
                }).dispose();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCode.TAKE_PHOTO:
                    final Uri resultUri = CameraBean.getINSTANCE().getUri();
                    UCrop.of(resultUri, resultUri)
                            .withMaxResultSize(400, 400)
                            .start(getContext(), this);
                    break;
                case RequestCode.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        //从相册选择后需要有个路径存放剪裁过的图片
                        final String pickCropPath = MallCameraa.createCropFile().getPath();
                        UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withMaxResultSize(400, 400)
                                .start(getContext(), this);
                    }
                    break;
                case RequestCode.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到剪裁后的数据进行处理
                    @SuppressWarnings("unchecked")
                    final IGlobalCallback<Uri> callback = CallbackManager
                            .getInstance()
                            .getCallback(CallbackType.ON_CROP);
                    if (callback != null) {
                        callback.excuteCallback(cropUri);
                    }
                    break;
                case RequestCode.CROP_ERROR:
                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
