package mprimavera.pearform.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.mprimavera.pearform.contracts.IFormValidationListener;
import com.mprimavera.pearform.model.fields.material.MaterialText;
import com.mprimavera.pearform.view.FormView;
import mprimavera.pearform.R;

public class Signup extends AppCompatActivity {
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

        // Fields
        MaterialText username = new MaterialText(this)
                .hint("Username")
                .error("Username not valid");

        MaterialText password = new MaterialText(this)
                .hint("Password")
                .error("password not valid");

        FormView formView = findViewById(R.id.formView);

        formView
            .add(title)
            .add(username)
            .add(password)
            .resetWith(resetButton)
            .validateWith(validateButton, new IFormValidationListener() {
                @Override public void onSuccess(Bundle result) {}
                @Override public void onError() {}
            })
            .build();
    }
}