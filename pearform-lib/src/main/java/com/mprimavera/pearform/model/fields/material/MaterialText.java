package com.mprimavera.pearform.model.fields.material;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.mprimavera.pearform.contracts.IValidator;
import com.mprimavera.pearform.R;
import com.mprimavera.pearform.model.FieldWidget;

public class MaterialText extends FieldWidget {
    protected TextInputLayout mInputLayout;
    protected TextInputEditText mInputText;
    protected IFieldValidator mValidator;
    protected String mError;
    protected Context mContext;

    public MaterialText(Context context) {
        super(context);
        init(context);
    }

    public MaterialText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MaterialText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MaterialText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        mLayout = (LinearLayout) inflate(getContext(), R.layout.form_material_text_field, this);
        mInputText = findViewById(R.id.input_text);
        mLeftIcon = findViewById(R.id.icon);
        mInputLayout = findViewById(R.id.layout);
    }

    public MaterialText resultKey(String key) {
        this.setResultKey(key);
        return this;
    }

    public MaterialText error(String error) {
        mError = error;
        return this;
    }

    public MaterialText error(int res) {
        mError = mContext.getResources().getString(res);
        return this;
    }

    public MaterialText hintColor(int style) {
        mInputLayout.setHintTextAppearance(style);
        return this;
    }

    public MaterialText hint(String hint) {
        mInputLayout.setHintEnabled(true);
        mInputLayout.setHintAnimationEnabled(true);
        mInputLayout.setHint(hint);
        return this;
    }

    public MaterialText hint(int res) {
        String hint = mContext.getResources().getString(res);
        mInputLayout.setHintEnabled(true);
        mInputLayout.setHintAnimationEnabled(true);
        mInputLayout.setHint(hint);
        return this;
    }

    @Override
    public boolean validate() {
        if(mValidator != null) {
            boolean valid = mValidator.validate(mInputText);
            if(!valid) {
                if(mError != null)
                    showError(mError);
            } else hideError();
            return valid;
        } else return true; // Default to NOT_REQUIRED
    }

    @Override
    public Bundle getValue() {
        Bundle bundle = new Bundle();
        bundle.putString(mResultKey, mInputText.getText().toString());
        return bundle;
    }

    public String getStringValue() {
        return mInputText.getText().toString();
    }

    @Override
    public void prefill(Bundle bundle) {
        if(bundle != null) {
            String prefill = bundle.getString(mResultKey);
            if(prefill != null) mInputText.setText(prefill);
        }
    }

    public MaterialText prefillWhen(boolean condition, Bundle bundle) {
        if(condition) prefill(bundle);
        return this;
    }

    @Override public void disable() {
        mInputText.setEnabled(false);
        mInputLayout.setEnabled(false);
    }

    @Override public void enable() {
        mInputText.setEnabled(true);
        mInputLayout.setEnabled(true);
    }

    @Override
    public void reset() {
        mInputText.setText(null);
    }

    @Override
    public void setValidator(IValidator validator) {
        mValidator = (IFieldValidator) validator;
    }

    public MaterialText validator(IValidator validator) {
        this.setValidator(validator);
        return this;
    }

    public interface IFieldValidator extends IValidator {
        boolean validate(TextInputEditText textView);
    }

    public void hideError() {
        mInputLayout.setErrorEnabled(false);
        mInputLayout.setError(null);
    }

    public void showError(String error) {
        mError = error;
        mInputLayout.setErrorEnabled(true);
        mInputLayout.setError(error);
    }

    public static MaterialText.IFieldValidator minLengthValidator(final int length) {
        return new IFieldValidator() {
            @Override
            public boolean validate(TextInputEditText textView) {
                String text = textView.getText().toString();
                if (text == null) return false;
                else {
                    if (text.length() > length) return true;
                    else return false;
                }
            }
        };
    }
}