package com.example.dblearning.assets.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dblearning.R;

public class SoalPraktikumAdapter extends RecyclerView.Adapter<SoalPraktikumAdapter.MyViewHolder> {



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_soal_praktikum, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        EditText edtJawaban;
        CardView btnCheck;
        TextView tvNomor,tvSoal;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            edtJawaban = itemView.findViewById(R.id.edt_jawaban);
            btnCheck = itemView.findViewById(R.id.btn_check);
            tvNomor = itemView.findViewById(R.id.tv_nomor);
            tvSoal = itemView.findViewById(R.id.tv_butir_soal);
        }
    }
}
