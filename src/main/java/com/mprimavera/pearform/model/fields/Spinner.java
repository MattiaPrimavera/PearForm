package com.mprimavera.pearform.model.fields;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.mprimavera.pearform.contracts.IValidator;
import com.mprimavera.pearform.R;
import com.mprimavera.pearform.model.FieldWidget;

public class Spinner extends FieldWidget {
    private android.widget.Spinner mSpinner;
    private IFieldValidator mValidator;

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
        mSpinner = findViewById(R.id.spinner);
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

    @Override
    public void setValidator(IValidator validator) {
        mValidator = (IFieldValidator) validator;
    }

    public static interface IFieldValidator extends IValidator {
    }
}
