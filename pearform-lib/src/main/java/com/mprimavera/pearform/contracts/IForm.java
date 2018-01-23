package com.mprimavera.pearform.contracts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.mprimavera.pearform.view.FormView;

/*
    A FormView object acts as a Controller, in such it does the same actions of the single fields, but it
    iterates on all the fields for performing those actions.

    Example: formView.getResult will iterate on all the fields by calling ifield.getValue() to collect the set of results
 */
public interface IForm {
    boolean validate();
    Bundle getResult();
    Bundle getParams();
    FormView prefillWhen(boolean prefill, Bundle bundle);
    FormView validateWith(View button, IFormValidationListener listener);
    FormView resetWith(View button);
    void reset();
    void init(Context context);
}
