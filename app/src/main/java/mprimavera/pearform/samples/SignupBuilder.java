package mprimavera.pearform.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.mprimavera.pearform.contracts.IFormValidationListener;
import com.mprimavera.pearform.view.FormBuilder;
import com.mprimavera.pearform.view.FormView;
import mprimavera.pearform.R;

public class SignupBuilder extends AppCompatActivity {
    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_signup);

        // Top text View
        TextView title = new TextView(this);
        title.setText("Signup here");

        // Buttons
        Button resetButton = findViewById(R.id.resetButton);
        Button validateButton = findViewById(R.id.signupButton);

        FormView formView = findViewById(R.id.formView);
        FormBuilder builder = formView.getFormBuilder();
        formView
                .add(title)
                .add(builder.text(USERNAME_KEY, "Username", "Username not valid")) // Username
                .add(builder.text(PASSWORD_KEY, "Password", "Password not valid")) // Password
                .resetWith(resetButton)
                .validateWith(validateButton, new IFormValidationListener() {
                    @Override public void onSuccess(Bundle result) {}
                    @Override public void onError() {}
                })
                .build();
    }
}