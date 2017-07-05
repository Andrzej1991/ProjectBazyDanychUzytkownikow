package com.example.andrzejdevcom.projectbazydanychuzytkownikow;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;

public class PanelAdmin extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    private ProgressDialog progressDialog;

    @BindView(R.id.wyszukajET)
    EditText wyszukajET;
    @BindView(R.id.wynikTV)
    TextView wynikTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_panel_admin);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
    }

    @OnClick(R.id.szukajB)
    public void szukajracownika() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Szukam pracownika...");
        myRef = database.getReference(wyszukajET.getText().toString());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                wynikTV.setText(value);
                if (TextUtils.isEmpty(wynikTV.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Brak pracownika o takim nazwisku", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Brak pracownika o takim nazwisku", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
