package com.mprimavera.pearform.contracts;
import android.os.Bundle;

public interface IField {
    boolean validate();
    Bundle getValue();
    void setResultKey(String key);
    void prefill(Bundle bundle);
    void reset();
    void setValidator(IValidator validator);
}
