package com.mprimavera.pearform.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mprimavera.pearform.contracts.IField;

/*
    Abstract definition of a PearForm Field Widget
 */
abstract public class FieldWidget extends LinearLayout implements IField {
    protected String mResultKey;
    protected ImageView mLeftIcon;
    protected Context mContext;
    protected LinearLayout mLayout;
    protected Bundle mBundle;
    protected Activity mActivity;
    protected Fragment mFragment;

    public FieldWidget(Context context) {
        super(context);
        mContext = context;
        mBundle = new Bundle();
    }

    public FieldWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mBundle = new Bundle();
    }

    public FieldWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mBundle = new Bundle();
    }

    public FieldWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        mBundle = new Bundle();
    }

    public FieldWidget activity(Activity activity) {
        mActivity = activity;
        return this;
    }

    public FieldWidget fragment(Fragment fragment) {
        mFragment = fragment;
        return this;
    }

    public FieldWidget resultKey(String key) {
        this.setResultKey(key);
        return this;
    }

    @Override public abstract boolean validate();
    @Override public abstract Bundle getValue();
    public void highlight() {}
    public void disableHighlight() {};

    @Override public void setResultKey(String key) {
        mResultKey = key;
    }
    public String getResultKey() { return mResultKey; }
    public boolean hasFieldBeenUpdated(Bundle bundle) {
        return false;
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
