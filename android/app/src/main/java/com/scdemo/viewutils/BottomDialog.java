package com.scdemo.viewutils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.scdemo.R;

public class BottomDialog extends Dialog {

    private TextView tvTitle;
    private TextView tvService;
    private TextView tvBundle;
    private TextView tvFile;
    private TextView tvCancel;

    private OnHintDialogListener mListener;

    public BottomDialog(Context context) {
        super(context, R.style.Theme_AppCompat_Dialog_Alert);
        setContentView(R.layout.dialog_bottom);
        // 设置宽高
        getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setWindowAnimations(R.style.dialogBottomAnimation);  //添加动画

        tvTitle = findViewById(R.id.dialog_title);
        tvService = findViewById(R.id.dialog_text_service);

        tvBundle = findViewById(R.id.dialog_text_bundle);
        tvFile = findViewById(R.id.dialog_text_file);
        tvCancel = findViewById(R.id.dialog_text_cancel);

        tvService.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onClickServer();
                dismiss();
            }
        });

        tvBundle.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onClickBundle();
                dismiss();
            }
        });

        tvFile.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onClickFile();
                dismiss();
            }
        });

        tvCancel.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onClickCancel();
                dismiss();
            }
        });

    }


    public void setOnListener(OnHintDialogListener listener) {
        this.mListener = listener;
    }


//    public static BottomDialog createSure(Activity activity) {
//        BottomDialog dialog = new BottomDialog(activity);
//        dialog.hideTitle();
//        dialog.setDoneVisibility(true);
//        return dialog;
//    }


    /**
     * 设置回调接口
     */

    public interface OnHintSureListener {
        void OnHintSure();
    }

    /**
     * 设置回调接口
     */

    public static class SimpleOnHintDialogListener implements OnHintDialogListener {


        @Override
        public void onClickServer() {

        }

        @Override
        public void onClickBundle() {

        }

        @Override
        public void onClickFile() {

        }

        @Override
        public void onClickCancel() {

        }
    }

    /**
     * 设置回调接口
     */

    public interface OnHintDialogListener {
        public void onClickServer();

        public void onClickBundle();

        public void onClickFile();

        public void onClickCancel();

    }
}