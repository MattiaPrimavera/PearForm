package com.mprimavera.pearform.model.fields.material;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.mprimavera.pearform.contracts.IValidator;
import com.mprimavera.pearform.R;
import com.mprimavera.pearform.model.FieldWidget;

public class MaterialText extends FieldWidget {
    private TextInputLayout mInputLayout;
    private TextInputEditText mInputText;
    private IFieldValidator mValidator;

    public MaterialText(Context context) {
        super(context);
    }

    public MaterialText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaterialText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MaterialText init(String hint) {
        inflate(getContext(), R.layout.form_material_text_field, this);
        mValidator = null;
        mInputLayout = (TextInputLayout) findViewById(R.id.layout);
        mInputLayout.setHintEnabled(true);
        mInputLayout.setHintAnimationEnabled(true);
        mInputLayout.setHint(hint);

        mInputText = (TextInputEditText) findViewById(R.id.input_text);
        mLeftIcon = (ImageView) findViewById(R.id.icon);
        return this;
    }

    @Override
    public boolean validate() {
        if(mValidator != null) {
            return mValidator.validate(mInputText);
        } else return true; // Default to NOT_REQUIRED
    }

    @Override
    public Bundle getValue() {
        Bundle bundle = new Bundle();
        bundle.putString(mResultKey, mInputText.getText().toString());
        return bundle;
    }

    @Override
    public void prefill(Bundle bundle) {
        String prefill = bundle.getString(mResultKey);
        if(prefill != null) mInputText.setText(prefill);
    }

    @Override
    public void reset() {
        mInputText.setText(null);
    }

    @Override
    public void setValidator(IValidator validator) {
        mValidator = (IFieldValidator) validator;
    }

    public interface IFieldValidator extends IValidator {
        boolean validate(TextInputEditText textView);
    }
}
