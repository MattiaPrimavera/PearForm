package com.mprimavera.pearform.model.fields.material;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.util.Log;

import com.mprimavera.pearform.R;
import com.mprimavera.pearform.contracts.IValidator;
import com.mprimavera.pearform.model.FieldWidget;

public class MaterialDoubleText extends FieldWidget {
    protected MaterialText mFirst, mSecond;
    protected IFieldValidator mValidator;
    protected String[] mHints;
    protected String[] mErrors;
    protected String[] mResultKeys;
    protected Context mContext;
    private Bundle mBundle;

    public MaterialDoubleText(Context context) {
        super(context);
        init(context);
    }
    public MaterialDoubleText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MaterialDoubleText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MaterialDoubleText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        mBundle = new Bundle();
        inflate(getContext(), R.layout.form_material_double_text_field, this);
        mFirst = findViewById(R.id.first);
        mSecond = findViewById(R.id.second);
    }

    public MaterialDoubleText resultKey(String key) {
        this.setResultKey(key);
        return this;
    }

    public MaterialDoubleText errors(String[] errors) {
        mErrors = errors;
        return this;
    }

    public MaterialDoubleText hints(String[] hints) {
        mHints = hints;
        return this;
    }

    public MaterialDoubleText resultKeys(String[] resulyKeys) {
        mResultKeys = resulyKeys;
        return this;
    }

    @Override public boolean validate() {
        return true;
    }

    @Override public Bundle getValue() {
        Bundle result = new Bundle();

        Bundle firstResult = mFirst.getValue();
        Bundle secondResult = mSecond.getValue();

        if(firstResult != null) result.putAll(firstResult);
        if(secondResult != null) result.putAll(secondResult);

        return result;
    }

    public MaterialDoubleText build() {
        Log.d("TEST", "Building MaterialDoubleText");
        mFirst
            .hint(mHints[0])
            .error(mErrors[0])
            .resultKey(mResultKeys[0])
            .hintColor(R.style.MaterialTextAppearance)
            .prefillWhen(true, mBundle);

        mSecond
            .hint(mHints[1])
            .error(mErrors[1])
            .resultKey(mResultKeys[1])
            .hintColor(R.style.MaterialTextAppearance)
            .prefillWhen(true, mBundle);

        return this;
    }

    @Override public void prefill(Bundle bundle) {
        if(bundle != null) {
            mBundle = bundle;
            mFirst.prefill(bundle);
            mSecond.prefill(bundle);
        }
    }

    @Override
    public void reset() {
        mFirst.reset();
        mSecond.reset();
    }

    @Override
    public void setValidator(IValidator validator) {
        mValidator = (IFieldValidator) validator;
    }

    public MaterialDoubleText validator(IValidator validator) {
        this.setValidator(validator);
        return this;
    }

    public interface IFieldValidator extends IValidator {
        boolean validate(TextInputEditText textView);
    }

    public static MaterialDoubleText.IFieldValidator minLengthValidator(final int length) {
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