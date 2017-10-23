package com.mprimavera.pearform.model.fields;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mprimavera.pearform.R;
import com.mprimavera.pearform.contracts.IValidator;
import com.mprimavera.pearform.model.FieldWidget;

public class TextInfo extends FieldWidget {
    private LinearLayout mLayout;
    private TextView mLabel, mText;

    public TextInfo(Context context) {
        super(context);
        init(context);
    }

    public TextInfo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextInfo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TextInfo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        inflate(context, R.layout.form_text_info_field, this);
        mLayout = findViewById(R.id.layout);
        mLabel = mLayout.findViewById(R.id.label);
        mText = mLayout.findViewById(R.id.text);
    }

    public void setText(String text) { mText.setText(text); }
    public void setLabel(String label) { mLabel.setText(label); }

    public TextInfo text(String text) {
        setText(text);
        return this;
    }

    public TextInfo label(String label) {
        setLabel(label);
        return this;
    }

    @Override public void prefill(Bundle bundle) {}
    @Override public void reset() {}
    @Override public void setValidator(IValidator validator) {}
    @Override public boolean validate() { return false; }
    @Override public Bundle getValue() { return null; }
}
