package com.mobilehealth.cardiac.core.tools.view.forms;

import android.os.Bundle;

public interface IField {
    boolean validate();
    Bundle getValue();
    void setResultKey(String key);
    void prefill(Bundle bundle);
    void reset();
}
