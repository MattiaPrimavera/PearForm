package com.mobilehealth.cardiac.core.tools.view.forms.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.mobilehealth.cardiac.R;
import com.mobilehealth.cardiac.core.tools.view.forms.IField;
import com.mobilehealth.cardiac.core.tools.view.forms.IForm;
import com.mobilehealth.cardiac.core.tools.view.forms.IFormValidationListener;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class FormView extends LinearLayout implements IForm {
    private List<IField> mFields;
    private Context mContext;
    private FormBuilder mFormBuilder;

    private LinearLayout mLayout;

    public FormView(Context context) {
        super(context);
        this.init();
    }

    public FormView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public FormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    public FormView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    public FormView add(IField field) {
        mFields.add(field);
        return this;
    }

    public FormView addIf(boolean condition, IField field) {
        if(condition) this.add(field);
        return this;
    }

    public FormView addWhen(boolean condition, IField field) {
        return this.addIf(condition, field);
    }

    public FormView withResultKeys(String[] resultBundleKeys) {
        for(int i = 0; i < resultBundleKeys.length; i++) {
            if(i >= mFields.size()) break; // Avoid IndexOutOfBound exceptions
            mFields.get(i).setResultKey(resultBundleKeys[i]);
        }
        return this;
    }

    @Override public void init() {
        inflate(getContext(), R.layout.widget_form_view, this);
        mFields = new ArrayList<>();
        mFormBuilder = new FormBuilder(getContext());
        mLayout = (LinearLayout) findViewById(R.id.layout);
    }

    public void build() {
        this.insertFields();
    }

    public FormBuilder getFormBuilder() {
        return this.mFormBuilder;
    }

    @Override public FormView prefillWhen(boolean prefill, Bundle bundle) {
        if(!prefill) return this;

        for (IField field : mFields) {
            field.prefill(bundle);
        }
        return this;
    }

    @Override
    public FormView validateWith(View button, IFormValidationListener listener) {
        button.setOnClickListener((View v) -> {
            if(validate()) {
                Bundle resultBundle = getResult();
                listener.onSuccess(resultBundle);
            } else {
                listener.onError();
            }
        });

        return this;
    }

    @Override public void reset() {
        for (IField field : mFields) {
            field.reset();
        }
    }

    private void insertFields() {
        if(mLayout.getChildCount() > 0) { // Resetting Linear Layout for instanciating form components
            mLayout.removeAllViews();
        }

        for (int i = 0; i < mFields.size(); i++) {
            View item = (View) mFields.get(i);
            mLayout.addView(item);
        }
    }

    @Override public boolean validate() {
        for (IField field : mFields) {
            boolean validField = field.validate();
            if(!validField) {
                return false;
            }
        }
        return true;
    }

    @Override public Bundle getResult() {
        Bundle bundle = new Bundle();
        for (IField field : mFields) {
            Bundle fieldResult = field.getValue();
            bundle.putAll(fieldResult);
        }
        return bundle;
    }

    @Override public Bundle getParams() {
        return null;
    }
}
