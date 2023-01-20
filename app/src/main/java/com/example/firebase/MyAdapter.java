package com.example.firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<UserModelClass> userList;
    MyClickInterface myClickInterface;

    public MyAdapter(ArrayList<UserModelClass> userList, MyClickInterface myClickInterface) {
        this.userList = userList;
        this.myClickInterface = myClickInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(userList.get(position).name);
        holder.txtEmail.setText(userList.get(position).email);
        holder.txtAddress.setText(userList.get(position).address);
        holder.txtPassword.setText(userList.get(position).password);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myClickInterface.onDeleteClick(userList.get(position).key);

            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myClickInterface.onEditClick(userList.get(position).key,userList.get(position).name,userList.get(position).email,userList.get(position).address,userList.get(position).password);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtEmail, txtAddress, txtPassword;
        ImageView imgDelete,imgEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtPassword = itemView.findViewById(R.id.txtPassword);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);
        }
    }

    public void updateData(ArrayList<UserModelClass> userList) {
        this.userList = new ArrayList();
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }
}
