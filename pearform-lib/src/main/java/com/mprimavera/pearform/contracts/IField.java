package com.mprimavera.pearform.contracts;
import android.os.Bundle;

public interface IField {
    boolean validate(); // Applies validation logic
    Bundle getValue(); // Returns the value of the input field
    void setResultKey(String key);
    void prefill(Bundle bundle); // Prefill input field with provided information
    void reset(); // Reset the input field
    void setValidator(IValidator validator); // Specify the logic to use for validation
    void disable();
    void enable();
}
