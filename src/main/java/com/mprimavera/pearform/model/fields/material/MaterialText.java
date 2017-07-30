package com.mobilehealth.cardiac.core.tools.view.forms.model.fields.material;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import com.mobilehealth.cardiac.R;
import com.mobilehealth.cardiac.core.tools.view.forms.model.FieldWidget;

public class MaterialText extends FieldWidget {
    private TextInputLayout mInputLayout;
    private TextInputEditText mInputText;

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
        mInputLayout = (TextInputLayout) findViewById(R.id.layout);
        mInputLayout.setHintEnabled(true);
        mInputLayout.setHintAnimationEnabled(true);
        mInputLayout.setHint(hint);

        mInputText = (TextInputEditText) findViewById(R.id.input_text);
        return this;
    }

    @Override
    public boolean validate() {
        return true;
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
}
