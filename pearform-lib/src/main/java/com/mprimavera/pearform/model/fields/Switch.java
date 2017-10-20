package com.mprimavera.pearform.model.fields;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mprimavera.pearform.R;
import com.mprimavera.pearform.contracts.IValidator;
import com.mprimavera.pearform.model.FieldWidget;
import com.mprimavera.pearform.tools.DrawableTools;

public class Switch extends FieldWidget {
    private SwitchCompat mSwitch;
    private IFieldValidator mValidator;
    private ImageView mLeftIcon;
    private TextView mTextView;

    public Switch(Context context) {
        super(context);
    }

    public Switch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Switch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Switch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public Switch init(String text) {
        mLayout = (LinearLayout) inflate(getContext(), R.layout.form_input_switch_field, this);
        mSwitch = findViewById(R.id.switcher);
        mLeftIcon = findViewById(R.id.icon);
        mTextView = findViewById(R.id.text);

        mTextView.setText(text);
        return this;
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    public void setIconResource(int resource) {
        Drawable drawable = DrawableTools.getDrawable(mContext, resource);
        mTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    public void setImageDrawable(Drawable drawable) {
        mLeftIcon.setImageDrawable(drawable);
    }
    public void setImageBitmap(Bitmap bitmap) {
        mLeftIcon.setImageBitmap(bitmap);
    }

    @Override public boolean validate() {
        return true;
    }

    @Override
    public Bundle getValue() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(mResultKey, mSwitch.isEnabled());
        return bundle;
    }

    @Override
    public void prefill(Bundle bundle) {
        if(bundle != null) {
            boolean enabled = bundle.getBoolean(mResultKey);
            mSwitch.setChecked(enabled);
        }
    }

    @Override public void disable() {
        mSwitch.setClickable(false);
    }

    @Override public void enable() {
        mSwitch.setClickable(true);
    }

    @Override
    public void reset() {
        mSwitch.setEnabled(false);
    }

    @Override
    public void setValidator(IValidator validator) {
        mValidator = (IFieldValidator) validator;
    }

    public static interface IFieldValidator extends IValidator {
    }
}
