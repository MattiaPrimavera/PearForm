package com.mobilehealth.cardiac.core.tools.view.forms;

import android.os.Bundle;

public interface IFormValidationListener {
    void onSuccess(Bundle result);
    void onError();
}
