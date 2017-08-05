package com.mprimavera.pearform.model.fields;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mprimavera.pearform.R;
import com.mprimavera.pearform.contracts.IValidator;
import com.mprimavera.pearform.model.FieldWidget;

public class CheckBox extends FieldWidget {
    private TextView label;
    private ImageView icon;
    private android.widget.CheckBox checkBox;

    public CheckBox(Context context) {
        super(context);
        init();
    }

    public CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public Bundle getValue() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(mResultKey, this.checkBox.isEnabled());
        return null;
    }

    private void init() {
        inflate(getContext(), R.layout.form_checkbox_field, this);
        this.icon = (ImageView) findViewById(R.id.icon);
        this.label = (TextView) findViewById(R.id.label);
        this.checkBox = (android.widget.CheckBox) findViewById(R.id.checkBox);
    }

    public void setChecked(boolean checked) { this.checkBox.setChecked(checked); }
    public void setLabel(int res) { this.label.setText(res); }
    public void setLabel(String label) { this.label.setText(label); }
    public void setIcon(int res) {
        this.icon.setImageResource(res);
        this.icon.setVisibility(View.VISIBLE);
    }

    @Override
    public void prefill(Bundle bundle) {
        boolean enabled = bundle.getBoolean(mResultKey);
        this.checkBox.setEnabled(enabled);
    }

    @Override public boolean validate() { return true; }
    @Override public void reset() { this.checkBox.setEnabled(false); }
    @Override public void setValidator(IValidator validator) {}
}