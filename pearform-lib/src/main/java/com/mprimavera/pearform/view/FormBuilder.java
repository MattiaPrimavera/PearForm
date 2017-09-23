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
                .validator(fieldValidator);
    }

    public MaterialText text(String resultBundleKey, String hint, String error, MaterialText.IFieldValidator fieldValidator) {
        return new MaterialText(mContext)
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
