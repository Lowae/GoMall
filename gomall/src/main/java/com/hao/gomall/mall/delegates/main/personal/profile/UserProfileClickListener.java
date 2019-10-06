package com.hao.gomall.mall.delegates.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.hao.gomall.mall.R;
import com.hao.gomall.mall.delegates.main.personal.list.ListBean;
import com.hao.gomall.mall.ui.date.DateDialogUtil;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.utils.callback.CallbackManager;
import com.hao.gomall_core.utils.callback.CallbackType;
import com.hao.gomall_core.utils.callback.IGlobalCallback;

public class UserProfileClickListener extends SimpleClickListener {

    private final MallDelegate delegate;

    private String[] mGenders = new String[]{"男", "女", "保密"};


    public UserProfileClickListener(MallDelegate delegate){
        this.delegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) adapter.getData().get(position);
        final int id = bean.getmId();
        switch (id){
            case 1:
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void excuteCallback(Uri arg) {
                        final ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                        Glide.with(delegate)
                                .load(arg)
                                .into(avatar);
                    }
                });
                delegate.startCameraWithCheck();
                break;
            case 2:
                break;
            case 3:
                setGenderDiaolog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setmDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(delegate.getContext());
                break;
            default:
                break;
        }
    }

    private void setGenderDiaolog(DialogInterface.OnClickListener listener){
        final AlertDialog.Builder builder = new AlertDialog.Builder(delegate.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener)
                .show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
