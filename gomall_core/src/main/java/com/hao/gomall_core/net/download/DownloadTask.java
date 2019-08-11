package com.hao.gomall_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.net.callback.IRequest;
import com.hao.gomall_core.net.callback.ISuccess;
import com.hao.gomall_core.utils.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class DownloadTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public DownloadTask(IRequest request, ISuccess success) {
        REQUEST = request;
        SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody responseBody = (ResponseBody) objects[2];
        final String name = (String) objects[3];
        final InputStream in = responseBody.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(in, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(in, downloadDir, name);
        }

    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Mall.getApplicationContext().startActivity(install);

        }
    }
}
