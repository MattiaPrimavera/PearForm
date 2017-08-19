package com.mprimavera.pearform.model.fields.material;

import android.app.Service;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.support.annotation.Nullable;
import android.view.inputmethod.InputMethodManager;
import com.mprimavera.pearform.R;

public class InputCode extends MaterialText {
    private String mHint;
    private int mMaxLength = 0;
    private boolean mFocus;

    public InputCode(Context context) {
        super(context);
    }

    public InputCode(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InputCode(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InputCode(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override protected void inflateLayout() {
        inflate(getContext(), R.layout.form_input_code_field, this);
    }

    // Call super.init() as soon as both hint / error are set
    public InputCode hint(String hint) {
        mHint = hint;
        if(mHint != null && mError != null) super.init(mHint, mError);
        return this;
    }

    public InputCode error(String error) {
        mError = error;
        if(mHint != null && mError != null) super.init(mHint, mError);
        return this;
    }

    public InputCode resultKey(String key) {
        mResultKey = key;
        return this;
    }

    public InputCode validateWith(IFieldValidator validator) {
        mValidator = validator;
        return this;
    }

    public InputCode build(Context context) {
        if(mMaxLength != 0) {
            mInputLayout.setCounterEnabled(true);
            mInputLayout.setCounterMaxLength(mMaxLength);
        }

        if(mFocus) {
            if (mInputText == null)
                return null;

            InputMethodManager imm = (InputMethodManager) context.getSystemService(Service.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mInputText, InputMethodManager.SHOW_IMPLICIT);
        }

        return this;
    }

    public InputCode focus() {
        mFocus = true;
        return this;
    }

    public InputCode length(int length) {
        mMaxLength = length;
        mValidator = new IFieldValidator() { // Check code length is exactly 'length'
            @Override
            public boolean validate(TextInputEditText textView) {
                String code = textView.getText().toString();
                if(code != null && code.length() == mMaxLength) {
                    hideError(); // Hide errors if field VALID
                    return true;
                } else { // Show Length error when field not VALID
                    mError = "Code length must be: " + mMaxLength;
                    showError(mError);
                    return false;
                }
            }
        };

        return this;
    }
}
