package com.hao.gomall.mall.ui.date;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateDialogUtil {

    public interface IDateListener{
        void onDateChange(String date);
    }

    private IDateListener mDateListener;

    public void setmDateListener(IDateListener mDateListener) {
        this.mDateListener = mDateListener;
    }

    public void showDialog(Context context){
        final LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(context);
        final DatePicker picker = new DatePicker(context);
        final LinearLayoutCompat.LayoutParams lp =
                new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                        LinearLayoutCompat.LayoutParams.MATCH_PARENT);
        picker.setLayoutParams(lp);
        picker.init(1990, 1, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                final SimpleDateFormat format = new SimpleDateFormat("yyyy年mm月dd日", Locale.getDefault());
                final String data = format.format(calendar.getTime());
                if (mDateListener != null){
                    mDateListener.onDateChange(data);
                }
            }
        });

        linearLayoutCompat.addView(picker);
        new AlertDialog.Builder(context)
                .setTitle("选择日期")
                .setView(linearLayoutCompat)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
