package com.inveitix.demo.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.inveitix.demo.R;

public class FavoDialog extends Dialog implements View.OnClickListener {
    private Button btnPositive;
    private Button btnNegative;
    private String title;
    private String message;
    private String negativeBtnTitle;
    private String positiveBtnTitle;

    private View.OnClickListener onPositiveClickListener;
    private View.OnClickListener onNegativeClickListener;

    public FavoDialog(@NonNull Context context) {
        super(context);
    }

    public FavoDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FavoDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.favo_dialog);
        setupViews();
    }

    private void setupViews() {
        btnPositive = findViewById(R.id.btn_ok);
        btnNegative = findViewById(R.id.btn_cancel);
        TextView txtDialogTitle = findViewById(R.id.txt_title);
        TextView txtDialogMessage = findViewById(R.id.txt_message);
        txtDialogTitle.setText(getTitle());
        txtDialogMessage.setText(getMessage());
        if (getNegativeBtnTitle() != null) {
            btnNegative.setText(getNegativeBtnTitle());
        }
        if (getPositiveBtnTitle() != null) {
            btnPositive.setText(getPositiveBtnTitle());
        }
        setupListeners();
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void setupListeners() {
        if (getOnPositiveClickListener() == null) {
            btnPositive.setOnClickListener(this);
        } else {
            btnPositive.setOnClickListener(getOnPositiveClickListener());
        }
        if (getOnNegativeClickListener() == null ) {
            btnNegative.setOnClickListener(this);
        } else {
            btnNegative.setOnClickListener(getOnNegativeClickListener());
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNegativeButton(String btnTitle) {
        setNegativeBtnTitle(btnTitle);
        setNegativeButton();
    }

    public String getNegativeBtnTitle() {
        return negativeBtnTitle;
    }

    public void setNegativeBtnTitle(String negativeBtnTitle) {
        this.negativeBtnTitle = negativeBtnTitle;
    }

    public void setNegativeButton() {
        btnNegative.setVisibility(View.VISIBLE);
    }

    public View.OnClickListener getOnPositiveClickListener() {
        return onPositiveClickListener;
    }

    public void setOnPositiveClickListener(View.OnClickListener onPositiveClickListener) {
        this.onPositiveClickListener = onPositiveClickListener;
    }

    public View.OnClickListener getOnNegativeClickListener() {
        return onNegativeClickListener;
    }

    public void setOnNegativeClickListener(View.OnClickListener onNegativeClickListener) {
        this.onNegativeClickListener = onNegativeClickListener;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPositiveBtnTitle() {
        return positiveBtnTitle;
    }

    public void setPositiveBtnTitle(String positiveBtnTitle) {
        this.positiveBtnTitle = positiveBtnTitle;
    }
}
