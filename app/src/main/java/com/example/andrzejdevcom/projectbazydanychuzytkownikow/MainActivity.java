package com.example.andrzejdevcom.projectbazydanychuzytkownikow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    @BindView(R.id.imieET)
    EditText imieET;
    @BindView(R.id.nazwiskoET)
    EditText nazwiskoET;
    @BindView(R.id.wiekET)
    EditText wiekET;
    @BindView(R.id.wagaET)
    EditText wagaET;
    @BindView(R.id.wysokoscET)
    EditText wysokoscET;
    @BindView(R.id.miejsceET)
    EditText miejsceET;
    @BindView(R.id.hobbyET)
    EditText hobbyET;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
    }

    @OnClick(R.id.sign_in_textView)
    public void signIn() {
        startActivity(new Intent(this, AdminActivity.class));
        finish();
    }

    @OnClick(R.id.saveBtn)
    public void saveUse() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        myRef = database.getReference(imieET.getText().toString());
        String user = " Imie: " + nazwiskoET.getText().toString() + " Wiek: " + wiekET.getText()
                .toString() + " Waga: " + wagaET.getText().toString() + "  Wysokość: " +
                wysokoscET.getText().toString()
                + " Miejsce: " + miejsceET.getText().toString() +
                " Hobby: " + hobbyET.getText().toString();
        progressDialog.show();
        myRef.setValue(user);
        final Handler handler = new Handler();
        final Runnable r = ,new Runnable() {
            public void run() {
                handler.postDelayed(this, 2000);
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Pracownik zapisany..", Toast.LENGTH_SHORT).show();
            }
        };

        handler.postDelayed(r, 2000);
    }
}
