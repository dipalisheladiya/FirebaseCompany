package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.firebase.databinding.ActivityMain2Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;
    ArrayList<UserModelClass> userList = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference myRef;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initView();
    }

    private void initView() {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.getRoot();
        myRef.child("UserTb");

        FirebaseDatabase.getInstance().getReference().child("UserTb").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot child : snapshot.getChildren()) {

                    UserModelClass studentdata = child.getValue(UserModelClass.class);

                    Log.e("TAG", "onDataChange: ==>" + child.getValue(UserModelClass.class).name);

                    userList.add(studentdata);

                }

                adapter.updateData(userList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MyClickInterface myClickInterface = new MyClickInterface() {
            @Override
            public void onDeleteClick(String key) {
                userList.clear();

                myRef = FirebaseDatabase.getInstance().getReference().child("UserTb").child(key);
                myRef.removeValue();

            }

            @Override
            public void onEditClick(String key, String name, String email, String address, String password) {

                Intent intent = new Intent(MainActivity2.this,DataEditActivity.class);
                intent.putExtra("key",key);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("address",address);
                intent.putExtra("password",password);
                startActivity(intent);

            }
        };

        adapter = new MyAdapter(userList, myClickInterface);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rcvUserList.setLayoutManager(manager);
        binding.rcvUserList.setAdapter(adapter);

    }
}