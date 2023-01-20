package com.example.firebase;

public class UserModelClass {

    public String name;
    public String email;
    public String address;
    public String password;
    public String key;

    public UserModelClass() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserModelClass(String name, String email,String address,String password,String key) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.key = key;
    }

}
