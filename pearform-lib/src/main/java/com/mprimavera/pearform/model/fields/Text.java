package com.mprimavera.pearform.model.fields;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mprimavera.pearform.contracts.IValidator;
import com.mprimavera.pearform.R;
import com.mprimavera.pearform.model.FieldWidget;

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
        mTextView = findViewById(R.id.input_text);
    }

    public void setTextColor(int color) {
        mTextView.setTextColor(color);
    }

    public void setText(String text) { mTextView.setText(text); }
    @Override public boolean validate() {
        return true;
    }

    @Override public void enable() {
        this.mTextView.setClickable(true);
    }

    @Override public void disable() {
        mTextView.setClickable(false);
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

    @Override
    public void setValidator(IValidator validator) {

    }
}
