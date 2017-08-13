package com.mprimavera.pearform.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.mprimavera.pearform.contracts.IField;
import com.mprimavera.pearform.contracts.IForm;
import com.mprimavera.pearform.contracts.IFormValidationListener;
import com.mprimavera.pearform.R;
import java.util.ArrayList;
import java.util.List;

public class FormView extends LinearLayout implements IForm {
    private List<FormRow> mRows;
    private Context mContext;
    private FormBuilder mFormBuilder;

    private LinearLayout mLayout;

    private class FormRow {
        private View mView;
        private boolean mIsField;

        public FormRow(View view, boolean isField) {
            mView = view;
            mIsField = isField;
        }

        public View getView() { return mView; }
        public void setView(View mView) { this.mView = mView; }
        public boolean isField() { return mIsField; }
        public void setIsField(boolean mIsField) { this.mIsField = mIsField; }
    }

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FormView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    public FormView divider(Drawable divider) {
        mLayout.setDividerDrawable(divider);
        return this;
    }

    public FormView add(View view) {
        FormRow row;
        try {
            IField field = (IField)view;
            row = new FormRow(view, true);
        } catch (ClassCastException e) {
            row = new FormRow(view, false);
        }
        mRows.add(row);
        return this;
    }

    public FormView addIf(boolean condition, IField field) {
        if(condition) {
            this.add((View) field);
        }
        return this;
    }

    public FormView addWhen(boolean condition, IField field) {
        return this.addIf(condition, field);
    }

    public FormView withResultKeys(String[] resultBundleKeys) {
        for(int i = 0; i < resultBundleKeys.length; i++) {
            if(i >= mRows.size()) break; // Avoid IndexOutOfBound exceptions
            FormRow row = mRows.get(i);
            if(row.isField()) {
                IField field = (IField) row.getView();
                field.setResultKey(resultBundleKeys[i]);
            }
        }
        return this;
    }

    @Override public void init() {
        inflate(getContext(), R.layout.widget_form_view, this);
        mRows = new ArrayList<>();
        mFormBuilder = new FormBuilder(getContext());
        mLayout = (LinearLayout) findViewById(R.id.layout);
    }

    public FormView build() {
        this.insertRows();
        return this;
    }

    public FormBuilder getFormBuilder() {
        return this.mFormBuilder;
    }

    @Override public FormView prefillWhen(boolean prefill, Bundle bundle) {
        if(!prefill) return this;

        for(FormRow row : mRows) {
            if(row.isField()) {
                IField field = (IField) row.getView();
                field.prefill(bundle);
            }
        }
        return this;
    }

    @Override
    public FormView validateWith(View button, final IFormValidationListener listener) {
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    Bundle resultBundle = getResult();
                    listener.onSuccess(resultBundle);
                } else {
                    listener.onError();
                }
            }
        });

        return this;
    }

    @Override public void reset() {
        for (FormRow row : mRows) {
            if(row.isField()) {
                IField field = (IField) row.getView();
                field.reset();
            }
        }
    }

    private void insertRows() {
        if(mLayout.getChildCount() > 0) { // Resetting Linear Layout for instanciating form components
            mLayout.removeAllViews();
        }

        for (int i = 0; i < mRows.size(); i++) {
            View item = mRows.get(i).getView();
            mLayout.addView(item);
        }
    }

    @Override public boolean validate() {
        for (FormRow row : mRows) {
            if(row.isField()) {
                IField field = (IField) row.getView();
                boolean validField = field.validate();
                if(!validField) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override public Bundle getResult() {
        Bundle bundle = new Bundle();
        for (FormRow row : mRows) {
            if(row.isField()) {
                IField field = (IField) row.getView();
                Bundle fieldResult = field.getValue();
                bundle.putAll(fieldResult);
            }
        }
        return bundle;
    }

    @Override public Bundle getParams() {
        return null;
    }
}
