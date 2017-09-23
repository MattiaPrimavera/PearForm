package com.mprimavera.pearform.view;

import android.content.Context;
import com.mprimavera.pearform.model.fields.Spinner;
import com.mprimavera.pearform.model.fields.Switch;
import com.mprimavera.pearform.model.fields.material.MaterialText;

public class FormBuilder {
    private Context mContext;

    public FormBuilder(Context context) {
        mContext = context;
    }

    public MaterialText text(String hint, String error) {
        return new MaterialText(mContext).init(hint, error);
    }

    public MaterialText text(String resultBundleKey, String hint, String error) {
        MaterialText text = new MaterialText(mContext).init(hint, error);
        text.setResultKey(resultBundleKey);
        return text;
    }

    public MaterialText text(String resultBundleKey, String hint, String error, int iconResource) {
        MaterialText text = new MaterialText(mContext).init(hint, error);
        text.setResultKey(resultBundleKey);
        text.setIconResource(iconResource);
        return text;
    }

    public MaterialText text(String resultBundleKey, String hint, MaterialText.IFieldValidator fieldValidator) {
        MaterialText text = new MaterialText(mContext).init(hint, null);
        text.setResultKey(resultBundleKey);
        text.setValidator(fieldValidator);
        return text;
    }

    public MaterialText text(String resultBundleKey, String hint, String error, MaterialText.IFieldValidator fieldValidator) {
        MaterialText text = new MaterialText(mContext).init(hint, error);
        text.setResultKey(resultBundleKey);
        text.setValidator(fieldValidator);
        return text;
    }

    public MaterialText text(String resultBundleKey, String hint, String error, int iconResource, MaterialText.IFieldValidator fieldValidator) {
        MaterialText text = new MaterialText(mContext).init(hint, error);
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
