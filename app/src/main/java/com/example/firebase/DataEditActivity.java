package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebase.databinding.ActivityDataEditBinding;
import com.example.firebase.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataEditActivity extends AppCompatActivity {

    ActivityDataEditBinding binding;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initView();
    }

    private void initView() {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        if (getIntent() != null) {

            String key = getIntent().getStringExtra("key");
            String name = getIntent().getStringExtra("name");
            String email = getIntent().getStringExtra("email");
            String address = getIntent().getStringExtra("address");
            String password = getIntent().getStringExtra("password");

            binding.edtName2.setText(name);
            binding.edtEmail2.setText(email);
            binding.edtAddress2.setText(address);
            binding.edtPassword2.setText(password);

            binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = binding.edtName2.getText().toString();
                    String email = binding.edtEmail2.getText().toString();
                    String address = binding.edtAddress2.getText().toString();
                    String password = binding.edtPassword2.getText().toString();

                    UserModelClass userModelClass = new UserModelClass(name, email, address, password, key);
                    myRef.getRoot().child("UserTb").child(key).setValue(userModelClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(DataEditActivity.this, "Data Update Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DataEditActivity.this, MainActivity2.class);
                                startActivity(intent);
                            } else {
                                Log.e("TAG", "onComplete: ===>" + task.getException().getMessage());
                            }
                        }
                    });

                }
            });
        }

    }
}