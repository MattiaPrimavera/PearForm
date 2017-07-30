package com.mobilehealth.cardiac.core.tools.view.forms;

import android.os.Bundle;
import android.view.View;

import com.mobilehealth.cardiac.core.tools.view.forms.view.FormView;

public interface IForm {
    boolean validate();
    Bundle getResult();
    Bundle getParams();
    FormView prefillWhen(boolean prefill, Bundle bundle);
    FormView validateWith(View button, IFormValidationListener listener);
    void reset();
    void init();
}
