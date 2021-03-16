package com.marcuskim.contactroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marcuskim.contactroom.MainActivity;
import com.marcuskim.contactroom.R;
import com.marcuskim.contactroom.model.Contact;


import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final List<Contact> contactList;
    private final Context context;
    private final OnContactClickListener contactClickListener;

    public RecyclerViewAdapter(List<Contact> contactList, Context context, OnContactClickListener contactClickListener) {
        this.contactList = contactList;
        this.context = context;
        this.contactClickListener = contactClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row, parent, false);

        return new ViewHolder(view, contactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.name.setText(contact.getName());
        holder.occupation.setText(contact.getOccupation());


    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(contactList.size());
    }

    public interface OnContactClickListener {
        void onContactClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView occupation;
        OnContactClickListener onContactClickListener;

        public ViewHolder(@NonNull View itemView, OnContactClickListener onContactClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.row_name_textview);
            occupation = itemView.findViewById(R.id.row_occupation_textview);
            this.onContactClickListener = onContactClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onContactClickListener.onContactClick(getAdapterPosition());
        }
    }
}
