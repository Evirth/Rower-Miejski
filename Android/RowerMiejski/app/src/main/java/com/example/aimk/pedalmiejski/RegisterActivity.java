package com.example.aimk.pedalmiejski;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.net.Uri;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    boolean result = true;
    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText numberEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Rejestracja");

        registerButton = (Button) findViewById(R.id.register_action_button);
        nameEditText = (EditText) findViewById(R.id.name_register);
        surnameEditText = (EditText) findViewById(R.id.surname_register);
        numberEditText = (EditText) findViewById(R.id.phone_number_register);
        emailEditText = (EditText) findViewById(R.id.email_register);
        passwordEditText = (EditText) findViewById(R.id.password_register);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirm_password_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
                if(result) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                    Toast.makeText(getApplicationContext(), "Rejestracja pomyślna. Proszę się zalogować", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void attemptRegister() {
        nameEditText.setError(null);
        surnameEditText.setError(null);
        numberEditText.setError(null);
        emailEditText.setError(null);
        passwordEditText.setError(null);
        confirmPasswordEditText.setError(null);

        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        String number = numberEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmpassword = confirmPasswordEditText.getText().toString();




    }

    public void onRadioButtonClicked(View view) {
    }
}
