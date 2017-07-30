package com.mobilehealth.cardiac.core.tools.view.forms.model.fields;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mobilehealth.cardiac.R;
import com.mobilehealth.cardiac.core.tools.view.forms.model.FieldWidget;

public class Text extends FieldWidget {
    private TextView mTextView;

    public Text(Context context) {
        super(context);
        this.init();
    }

    public Text(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public Text(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    public Text(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    public void init() {
        inflate(getContext(), R.layout.form_text_field, this);
        mTextView = (TextView) findViewById(R.id.input_text);
    }

    @Override public boolean validate() {
        return true;
    }

    @Override
    public Bundle getValue() {
        Bundle bundle = new Bundle();
        bundle.putString(mResultKey, mTextView.getText().toString());
        return bundle;
    }

    @Override
    public void prefill(Bundle bundle) {
        String text = bundle.getString(mResultKey);
        if(text != null) mTextView.setText(text);
    }

    @Override
    public void reset() {
        mTextView.setText(null);
    }
}
