package com.monsun.suiicao.views.trash;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.monsun.suiicao.R;

public class themsv extends AppCompatActivity {
    private EditText username,password,firstname,lastname,dob,classid,khoa,cccd,email;
    private RadioButton it,eco,male,female;
    private String user,pass,major,firstName,lastName,gender,emaill,dateofbirth,classidd,K,CCCD;
    private sinhvien sv;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themsv);
        refWidget();
        savedata();
    }

    private void savedata() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    user = username.getText().toString();
                    pass = password.getText().toString();
                    major = it.isChecked()?it.getText().toString():eco.getText().toString();
                    firstName = firstname.getText().toString();
                    lastName = lastname.getText().toString();
                    gender = male.isChecked()?male.getText().toString():female.getText().toString();
                    emaill = email.getText().toString();
                    dateofbirth = dob.getText().toString();
                    classidd = classid.getText().toString();
                    K = khoa.getText().toString();
                    CCCD = cccd.getText().toString();
                    if (pass.isEmpty())
                        pass = "123";
                    if (CCCD.isEmpty())
                        CCCD = "";
                    if (emaill.isEmpty())
                        emaill ="";
                    sv = new sinhvien(user,pass,major,firstName,lastName,gender,emaill,dateofbirth,classidd,K,CCCD);
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Boolean res = db.collection("users").document(sv.getUsername().toString()).set(sv).isSuccessful();
                    Toast.makeText(themsv.this, "Lưu Thành công", Toast.LENGTH_SHORT).show();
                }
               catch (Exception e){
                   Toast.makeText(themsv.this, "" +e.toString(), Toast.LENGTH_SHORT).show();
               }
            }
        });
    }


    public void refWidget()
    {
        submit = findViewById(R.id.submit);
        username = findViewById(R.id.stu_id);
        password = findViewById(R.id.stu_password);
        it = findViewById(R.id.major_IT);
        eco = findViewById(R.id.major_economic);
        firstname = findViewById(R.id.stu_firstname);
        lastname = findViewById(R.id.stu_lastname);
        male = findViewById(R.id.gender_male);
        female = findViewById(R.id.gender_female);
        dob = findViewById(R.id.stu_dob);
        classid = findViewById(R.id.stu_class);
        khoa = findViewById(R.id.stu_k);
        cccd = findViewById(R.id.stu_cccd);
        email = findViewById(R.id.stu_email);
    }
}