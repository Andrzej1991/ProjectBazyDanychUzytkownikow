package com.example.andrzejdevcom.projectbazydanychuzytkownikow;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminActivity extends AppCompatActivity {

    @BindView(R.id.hasloET)
    EditText hasloET;
    @BindView(R.id.logitET)
    EditText loginET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.zalogujBtn)
    public void signIn() {
        if (Objects.equals(hasloET.getText().toString().trim(), "admin") && Objects.equals(loginET
                .getText().toString().trim(), "admin")) {
            startActivity(new Intent(this, PanelAdmin.class));
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Sprawdz dane logowania", Toast.LENGTH_SHORT).show();
        }
    }
}
