package com.mprimavera.pearform.model.fields;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;
import com.mprimavera.pearform.R;
import com.mprimavera.pearform.contracts.IValidator;
import com.mprimavera.pearform.model.FieldWidget;

public class TextArea extends FieldWidget {
    private EditText mText;
    private TextView mTitle;
    private IFieldValidator mValidator;

    public TextArea(Context context) {
        super(context);
        this.init();
    }

    public TextArea(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public TextArea(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    public TextArea(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    public void init() {
        inflate(getContext(), R.layout.form_input_text_area, this);
        mTitle = findViewById(R.id.title);
        mText = findViewById(R.id.text);
    }

    @Override public boolean validate() {
        return true;
    }
    @Override public Bundle getValue() {
        Bundle bundle = new Bundle();
        bundle.putString(mResultKey, mText.getText().toString());
        return bundle;
    }

    @Override
    public void prefill(Bundle bundle) {
//        boolean enabled = bundle.getBoolean(mResultKey);
    }

    @Override public void reset() {}

    @Override public void setValidator(IValidator validator) {
        mValidator = (IFieldValidator) validator;
    }

    public static interface IFieldValidator extends IValidator {}
}
