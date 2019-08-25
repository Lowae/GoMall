package com.hao.gomall_core.ui.camera;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.FileUtils;
import com.hao.gomall_core.R;
import com.hao.gomall_core.delegates.PermissionCheckedDelegate;
import com.hao.gomall_core.utils.FileUtil;

import java.io.File;
import java.util.Objects;

/**
 * 照片处理类
 */
public class CameraHandler implements View.OnClickListener {

    private final AlertDialog dialog;
    private final PermissionCheckedDelegate checkedDelegate;


    public CameraHandler(PermissionCheckedDelegate checkedDelegate) {
        this.dialog = new AlertDialog.Builder(Objects.requireNonNull(checkedDelegate.getContext())).create();
        this.checkedDelegate = checkedDelegate;
    }

    public final void beginCameraDialog() {
        dialog.show();
        final Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
        }
    }

    private String getPhotoName(){
        return FileUtil.getFileNameByTime("IMG", "jpg");
    }

    private void takePhoto(){
        final String currentPhotoName = getPhotoName();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final File tempFile = new File(FileUtil.CAMERA_PHOTO_DIR, currentPhotoName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            final ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());
            final Uri uri = checkedDelegate.getContext().getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            final File realFile = FileUtils.getFileByPath(FileUtil.getRealFilePath(checkedDelegate.getContext(), uri));
            final Uri realUri = Uri.fromFile(realFile);
            CameraBean.getINSTANCE().setUri(realUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }else {
            final Uri fileUri = Uri.fromFile(tempFile);
            CameraBean.getINSTANCE().setUri(fileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        }

        checkedDelegate.startActivityForResult(intent, RequestCode.TAKE_PHOTO);
    }

    private void pickPhoto(){
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        checkedDelegate.startActivityForResult(Intent.createChooser(intent, "选择获取图片的方式"), RequestCode.PICK_PHOTO);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.photodialog_btn_cancel) {
            dialog.cancel();
        }else if (i == R.id.photodialog_btn_take) {
            takePhoto();
            dialog.cancel();
        } else if (i == R.id.photodialog_btn_native) {
            pickPhoto();
            dialog.cancel();
        }
    }
}
