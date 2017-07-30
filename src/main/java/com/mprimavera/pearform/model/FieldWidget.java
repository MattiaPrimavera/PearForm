package com.mobilehealth.cardiac.core.tools.view.forms.model;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.mobilehealth.cardiac.core.tools.view.forms.IField;

abstract public class FieldWidget extends LinearLayout implements IField {
    protected String mResultKey;

    public FieldWidget(Context context) {
        super(context);
    }

    public FieldWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FieldWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FieldWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override public abstract boolean validate();
    @Override public abstract Bundle getValue();

    @Override public void setResultKey(String key) {
        mResultKey = key;
    }
}
