package mprimavera.pearform.samples;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.mprimavera.pearform.contracts.IFormValidationListener;
import com.mprimavera.pearform.model.fields.material.MaterialText;
import com.mprimavera.pearform.view.FormBuilder;
import com.mprimavera.pearform.view.FormView;
import mprimavera.pearform.R;

public class PersonalInfo extends AppCompatActivity {
    private static final String FIRST_NAME_KEY = "firstName";
    private static final String LAST_NAME_KEY = "lastName";
    private static final String ZIP_CODE_KEY = "zipCode";
    private static final String CITY_KEY = "city";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_signup);

        // Top text View
        TextView title = new TextView(this);
        title.setText("PersonalInfos");

        String error = "Min length must be 2";
        MaterialText.IFieldValidator minLengthValidator = new MaterialText.IFieldValidator() {
            @Override
            public boolean validate(TextInputEditText textView) {
                String text = textView.getText().toString();
                if(text == null || text.length() < 2 )
                    return false;
                else return true;
            }
        };

        // Buttons
        Button resetButton = findViewById(R.id.resetButton);
        Button validateButton = findViewById(R.id.signupButton);

        FormView formView = findViewById(R.id.formView);
        FormBuilder builder = formView.getFormBuilder();
        formView
                .add(title)
                .add(builder.text(FIRST_NAME_KEY, "First Name", error, minLengthValidator)) // First Name
                .add(builder.text(LAST_NAME_KEY, "Last Name", error, minLengthValidator)) // Last Name
                .add(builder.text(ZIP_CODE_KEY, "Zip Code", error, minLengthValidator)) // Zip Code
                .add(builder.text(CITY_KEY, "City", error, minLengthValidator)) // City
                .resetWith(resetButton)
                .validateWith(validateButton, new IFormValidationListener() {
                    @Override public void onSuccess(Bundle result) {}
                    @Override public void onError() {}
                })
                .build();
    }
}