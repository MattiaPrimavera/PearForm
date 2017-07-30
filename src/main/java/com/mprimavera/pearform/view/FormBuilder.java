package com.mobilehealth.cardiac.core.tools.view.forms.view;

import android.content.Context;
import com.mobilehealth.cardiac.core.tools.view.forms.model.fields.Spinner;
import com.mobilehealth.cardiac.core.tools.view.forms.model.fields.material.MaterialText;

public class FormBuilder {
    private Context mContext;

    public FormBuilder(Context context) {
        mContext = context;
    }

    public MaterialText text(String hint) {
        return new MaterialText(mContext).init(hint);
    }

    public MaterialText text(String resultBundleKey, String hint) {
        MaterialText text = new MaterialText(mContext).init(hint);
        text.setResultKey(resultBundleKey);
        return text;
    }

    public Spinner spinner(String resultBundleKey) {
        Spinner spinner = new Spinner(mContext);
        spinner.setResultKey(resultBundleKey);
        return spinner;
    }
}
