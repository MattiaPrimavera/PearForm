package com.mprimavera.pearform.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.mprimavera.pearform.contracts.IField;
import com.mprimavera.pearform.contracts.IForm;
import com.mprimavera.pearform.contracts.IFormValidationListener;
import com.mprimavera.pearform.R;
import com.mprimavera.pearform.tools.DrawableTools;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class FormView extends LinearLayout implements IForm {
    private List<FormRow> mRows;
    protected Context mContext;
    private FormBuilder mFormBuilder;
    private Bundle mBundle;
    private LinearLayout mLayout;
    private boolean mClickable, mAlreadyBuilt;

    private class FormRow {
        private View mView;
        private boolean mIsField;

        public FormRow(View view, boolean isField) {
            mView = view;
            mIsField = isField;
        }

        public View getView() { return mView; }
        public void setView(View mView) { this.mView = mView; }
        public boolean isField() { return mIsField; }
        public void setIsField(boolean mIsField) { this.mIsField = mIsField; }
    }

    public FormView(Context context) {
        super(context);
        this.init(context);
    }

    public FormView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public FormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FormView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init(context);
    }

    public FormView divider(Drawable divider) {
        mLayout.setDividerDrawable(divider);
        return this;
    }

    public FormView divider(int dividerRes) {
        mLayout.setDividerDrawable(DrawableTools.getDrawable(mContext, dividerRes));
        return this;
    }

    public FormView add(View view) {
        if(mAlreadyBuilt) return this;

        FormRow row;
        try {
            IField field = (IField)view;
            row = new FormRow(view, true);
        } catch (ClassCastException e) {
            row = new FormRow(view, false);
        }
        mRows.add(row);
        return this;
    }

    public FormView addIf(boolean condition, IField field) {
        if(mAlreadyBuilt) return this;

        if(condition) {
            this.add((View) field);
        }
        return this;
    }

    public FormView addWhen(boolean condition, IField field) {
        if(mAlreadyBuilt) return this;

        return this.addIf(condition, field);
    }

    public FormView withResultKeys(String[] resultBundleKeys) {
        for(int i = 0; i < resultBundleKeys.length; i++) {
            if(i >= mRows.size()) break; // Avoid IndexOutOfBound exceptions
            FormRow row = mRows.get(i);
            if(row.isField()) {
                IField field = (IField) row.getView();
                field.setResultKey(resultBundleKeys[i]);
            }
        }
        return this;
    }

    @Override public void init(Context context) {
        inflate(getContext(), R.layout.widget_form_view, this);
        mContext = context;
        mRows = new ArrayList<>();
        mFormBuilder = new FormBuilder(getContext());
        mLayout = findViewById(R.id.layout);
        mClickable = true;
        mAlreadyBuilt = false;
    }

    public FormView build() {
        if(!mAlreadyBuilt)
            this.insertRows();

        mAlreadyBuilt = true;
//        else prefill(mBundle);
        return this;
    }

    public FormBuilder getFormBuilder() {
        return this.mFormBuilder;
    }

    @Override public FormView prefillWhen(boolean prefill, Bundle bundle) {
        if(mAlreadyBuilt) return this;

        if(!prefill) return this;

        prefill(bundle);
        return this;
    }

    public void prefill(Bundle bundle) {
        mBundle = bundle;
        for(FormRow row : mRows) {
            if(row.isField()) {
                IField field = (IField) row.getView();
                field.prefill(bundle);
            }
        }
    }

    public FormView validateWith(MenuItem menuItem, final IFormValidationListener listener) {
        if(listener == null) return null;
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(validate()) {
                    Bundle resultBundle = getResult();
                    listener.onSuccess(resultBundle);
                } else listener.onError();
                return false;
            }
        });

        return this;
    }

    public FormView background(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            this.setBackgroundColor(getContext().getColor(color));
        else
            this.setBackgroundColor(color);

        return this;
    }

    public Observable<Bundle> rxValidateWith(final View button) {
        final Observable formResult = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(final @NonNull ObservableEmitter e) throws Exception {
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                if(validate()) {
                    Bundle resultBundle = getResult();
                    e.onNext(resultBundle);
                    e.onComplete();
                } else e.onError(new Exception("Form not Valid"));
            }
            });
            }
        });

        return formResult;
    }

    @Override
    public FormView validateWith(View button, final IFormValidationListener listener) {
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            if(validate()) {
                Bundle resultBundle = getResult();
                listener.onSuccess(resultBundle);
            } else {
                listener.onError();
            }
            }
        });

        return this;
    }

    @Override
    public FormView resetWith(View button) {
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        return this;
    }

    @Override public void reset() {
        for (FormRow row : mRows) {
            if(row.isField()) {
                IField field = (IField) row.getView();
                field.reset();
            }
        }
    }

    private void insertRows() {
        if(mLayout.getChildCount() > 0) { // Resetting Linear Layout for instanciating form components
            mLayout.removeAllViews();
        }

        for (int i = 0; i < mRows.size(); i++) {
            View item = mRows.get(i).getView();
            mLayout.addView(item);
        }
    }

    @Override public boolean validate() {
        for (FormRow row : mRows) {
            if(row.isField()) {
                IField field = (IField) row.getView();
                boolean validField = field.validate();
                if(!validField) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override public void setClickable(boolean clickable) {
        mClickable = clickable;
        for (FormRow row : mRows) {
            if(row.isField()) {
                IField field = (IField) row.getView();
                if(clickable) field.enable();
                else field.disable();
            }
        }
    }

    @Override public boolean isClickable() {
        return mClickable;
    }

    @Override public Bundle getResult() {
        Bundle bundle = new Bundle();
        for (FormRow row : mRows) {
            if(row.isField()) {
                IField field = (IField) row.getView();
                Bundle fieldResult = field.getValue();
                if(fieldResult != null)
                    bundle.putAll(fieldResult);
            }
        }
        return bundle;
    }

    @Override public Bundle getParams() {
        return null;
    }
}
