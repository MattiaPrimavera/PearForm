package com.mprimavera.pearform.view;

import android.content.Context;
import android.widget.Gallery;

import com.mprimavera.pearform.model.fields.Spinner;
import com.mprimavera.pearform.model.fields.Switch;
import com.mprimavera.pearform.model.fields.material.MaterialText;

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

    public MaterialText text(String resultBundleKey, String hint, int iconResource) {
        MaterialText text = new MaterialText(mContext).init(hint);
        text.setResultKey(resultBundleKey);
        text.setIconResource(iconResource);
        return text;
    }

    public MaterialText text(String resultBundleKey, String hint, MaterialText.IFieldValidator fieldValidator) {
        MaterialText text = new MaterialText(mContext).init(hint);
        text.setResultKey(resultBundleKey);
        text.setValidator(fieldValidator);
        return text;
    }

    public MaterialText text(String resultBundleKey, String hint, int iconResource, MaterialText.IFieldValidator fieldValidator) {
        MaterialText text = new MaterialText(mContext).init(hint);
        text.setResultKey(resultBundleKey);
        text.setValidator(fieldValidator);
        text.setIconResource(iconResource);
        return text;
    }

    public Spinner spinner(String resultBundleKey) {
        Spinner spinner = new Spinner(mContext);
        spinner.setResultKey(resultBundleKey);
        return spinner;
    }

    public Switch switcher(String resultBundleKey, String text, int iconResource) {
        Switch switcher = new Switch(mContext).init(text);
        switcher.setResultKey(resultBundleKey);
        switcher.setIconResource(iconResource);
        return switcher;
    }
}
