package com.mobilehealth.cardiac.core.tools.view.forms.model.fields;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.mobilehealth.cardiac.R;
import com.mobilehealth.cardiac.core.tools.view.forms.model.FieldWidget;

public class Spinner extends FieldWidget {
    private android.widget.Spinner mSpinner;

    public Spinner(Context context) {
        super(context);
        this.init();
    }

    public Spinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public Spinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    public Spinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    public void init() {
        inflate(getContext(), R.layout.form_input_spinner_field, this);
        mSpinner = (android.widget.Spinner) findViewById(R.id.spinner);
    }

    @Override public boolean validate() {
        return true;
    }

    @Override
    public Bundle getValue() {
        Bundle bundle = new Bundle();
        bundle.putString(mResultKey, Integer.toString(mSpinner.getSelectedItemPosition()));
        return bundle;
    }

    @Override
    public void prefill(Bundle bundle) {
        String value = bundle.getString(mResultKey);
        int selection = Integer.parseInt(value);
        mSpinner.setSelection(selection);
    }

    @Override
    public void reset() {
        mSpinner.setSelection(0);
    }
}
