package com.mprimavera.pearform.contracts;

import android.os.Bundle;

public interface IFormValidationListener {
    void onSuccess(Bundle result);
    void onError();
}
