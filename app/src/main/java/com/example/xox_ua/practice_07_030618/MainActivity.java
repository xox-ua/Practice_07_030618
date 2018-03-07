package com.example.xox_ua.practice_07_030618;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText etLogin;
    public EditText etPass;
    public Button btnLogin;
    public TextView tvSignUp;
    public TextView tvForget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // фиксируем экран (запрет поворота)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etPass = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvSignUp = (TextView) findViewById(R.id.tvSignup);
        tvForget = (TextView) findViewById(R.id.tvForget);

        // нажатие кнопки LogIn и обработка полей EditText (пустое, длина пароля меньше 6)
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp_login = etLogin.getText().toString();
                String temp_pass = etPass.getText().toString();
                int pass_length = etPass.getText().length();

                if (temp_login.matches("")) {
                    etLogin.requestFocus();
                    etLogin.setError(getString(R.string.error_login));
                } else if (temp_pass.matches("")) {
                    etPass.setError(getString(R.string.error_pass));
                } else if (6 >= pass_length) {
                    Toast.makeText(getApplicationContext(), R.string.toast, Toast.LENGTH_SHORT).show();
                } else {
                    etLogin.setError(null);
                    etPass.setError(null);
                    // осуществляем переход
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                }
            }
        });

        // нажатие SignUp
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toast1, Toast.LENGTH_SHORT).show();
            }
        });

        // Нажатие Forget
        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toast2, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
