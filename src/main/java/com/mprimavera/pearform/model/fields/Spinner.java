package com.mprimavera.pearform.model.fields;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import com.mprimavera.pearform.contracts.IValidator;
import com.mprimavera.pearform.R;
import com.mprimavera.pearform.model.FieldWidget;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class Spinner extends FieldWidget {
    private MaterialBetterSpinner mSpinner;
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
        mSpinner = findViewById(R.id.materialSpinner);
    }

    public Spinner hintColor(int color) {
        mSpinner.setHintTextColor(color); return this;
    }

    public Spinner hint(int hint) {
        mSpinner.setHint(hint);
        return this;
    }

    public Spinner hint(String hint) {
        mSpinner.setHint(hint);
        return this;
    }

    public Spinner floatingHint(String hint) {
        mSpinner.setFloatingLabelText(hint);
        return this;
    }

    public Spinner floatingHint(String hint, int color) {
        mSpinner.setFloatingLabelText(hint);
        mSpinner.setFloatingLabelTextColor(color);
        return this;
    }

    public Spinner validateWith(IValidator validator) {
        mValidator = (IFieldValidator) validator;
        return this;
    }

    public Spinner elements(String[] elements) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, elements);
        mSpinner.setAdapter(arrayAdapter);
        return this;
    }

    @Override public boolean validate() {
        return true;
    }

    @Override
    public Bundle getValue() {
        Bundle bundle = new Bundle();
//        bundle.putString(mResultKey, Integer.toString(mSpinner.()));
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
