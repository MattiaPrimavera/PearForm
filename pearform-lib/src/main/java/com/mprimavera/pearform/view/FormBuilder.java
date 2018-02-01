package com.mprimavera.pearform.view;

import android.content.Context;

import com.mprimavera.pearform.R;
import com.mprimavera.pearform.model.fields.Spinner;
import com.mprimavera.pearform.model.fields.Switch;
import com.mprimavera.pearform.model.fields.TextInfo;
import com.mprimavera.pearform.model.fields.material.MaterialDoubleText;
import com.mprimavera.pearform.model.fields.material.MaterialText;

/*
    Helper class for easily building fields
 */
public class FormBuilder {
    private Context mContext;

    public FormBuilder(Context context) {
        mContext = context;
    }

    public MaterialText text(String hint, String error) {
        return new MaterialText(mContext)
            .hint(hint)
            .error(error);
    }

    public MaterialText text(String resultBundleKey, String hint, String error) {
        return new MaterialText(mContext)
            .hint(hint)
            .error(error)
            .resultKey(resultBundleKey);
    }

    public MaterialText text(String resultBundleKey, String hint, String error, int iconResource) {
        return new MaterialText(mContext)
            .hint(hint)
            .error(error)
            .resultKey(resultBundleKey);
    }

    public MaterialText text(String resultBundleKey, String hint, MaterialText.IFieldValidator fieldValidator) {
        return new MaterialText(mContext)
            .hint(hint)
            .resultKey(resultBundleKey)
            .validator(fieldValidator)
            .hintColor(R.style.MaterialTextAppearance);
    }

    public MaterialText text(String resultBundleKey, String hint, String error, MaterialText.IFieldValidator fieldValidator) {
        return new MaterialText(mContext)
            .hint(hint)
            .error(error)
            .resultKey(resultBundleKey)
            .validator(fieldValidator);
    }

    public MaterialText email(String resultBundleKey, String hint, String error, MaterialText.IFieldValidator fieldValidator) {
        return new MaterialText(mContext)
            .typeEmail()
            .hint(hint)
            .error(error)
            .resultKey(resultBundleKey)
            .validator(fieldValidator);
    }

    public MaterialText text(String resultBundleKey, String hint, String error, int iconResource, MaterialText.IFieldValidator fieldValidator) {
        return new MaterialText(mContext)
            .hint(hint)
            .error(error)
            .resultKey(resultBundleKey)
            .validator(fieldValidator);
    }

    public TextInfo textInfo(String resultBundleKey, String label) {
        return new TextInfo(mContext)
            .resultKey(resultBundleKey)
            .label(label);
    }

    public MaterialDoubleText doubleText(String []hints, String []errors, String []resultKeys) {
        return new MaterialDoubleText(mContext)
            .hints(hints)
            .errors(errors)
            .resultKeys(resultKeys)
            .build();
    }

    public Spinner spinner(String resultBundleKey) {
        Spinner spinner = new Spinner(mContext);
        spinner.setResultKey(resultBundleKey);
        return spinner;
    }

    public Switch switcher(String resultBundleKey, String text) {
        Switch switcher = new Switch(mContext)
            .text(text)
            .resultKey(resultBundleKey);
        return switcher;
    }

    public Switch switcher(String resultBundleKey, String text, int iconResource) {
        return switcher(resultBundleKey, text)
            .icon(iconResource);
    }
}
