package com.mprimavera.pearform.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mprimavera.pearform.contracts.IField;

abstract public class FieldWidget extends LinearLayout implements IField {
    protected String mResultKey;
    protected ImageView mLeftIcon;
    protected Context mContext;
    protected LinearLayout mLayout;

    public FieldWidget(Context context) {
        super(context);
        mContext = context;
    }

    public FieldWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public FieldWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public FieldWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
    }

    @Override public abstract boolean validate();
    @Override public abstract Bundle getValue();

    @Override public void setResultKey(String key) {
        mResultKey = key;
    }


    public void setIconResource(int resource) {
        mLeftIcon.setImageResource(resource);
        this.showIcon();
    }

    public void setImageDrawable(Drawable drawable) {
        mLeftIcon.setImageDrawable(drawable);
        this.showIcon();
    }

    public void setImageBitmap(Bitmap bitmap) {
        mLeftIcon.setImageBitmap(bitmap);
        this.showIcon();
    }

    public void showIcon() {
        mLeftIcon.setVisibility(View.VISIBLE);
    }

    @Override public void enable() {}
    @Override public void disable() {}
}
