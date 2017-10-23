package com.mprimavera.pearform.contracts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.mprimavera.pearform.view.FormView;

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
