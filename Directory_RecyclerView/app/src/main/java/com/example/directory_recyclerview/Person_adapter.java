package com.example.directory_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class Person_adapter extends RecyclerView.Adapter<Person_adapter.ViewHolder> {

    private ArrayList<Person> people;
    ItemClicked activity;
    public interface ItemClicked
    {
        void onItemClicked(int index);
    }

    public Person_adapter(Context context, ArrayList<Person> list){
        people = list;
        activity = (ItemClicked) context;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvname = itemView.findViewById(R.id.tvname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    activity.onItemClicked(people.indexOf((Person)view.getTag()));

                }
            });
        }


    }



    @NonNull
    @Override
    public Person_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Person_adapter.ViewHolder holder, int position) {
        holder.itemView.setTag(people.get(position));
        holder.tvname.setText(people.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
