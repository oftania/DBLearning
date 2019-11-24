package com.example.dblearning.assets.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dblearning.R;
import com.example.dblearning.assets.database.PraktikumDatabase;
import com.example.dblearning.assets.database.model.TablePraktikum;
import com.example.dblearning.quiz.PraktikumFragment;

import java.util.List;
import java.util.Objects;

public class SoalPraktikumAdapter extends RecyclerView.Adapter<SoalPraktikumAdapter.MyViewHolder> {
    private Context context;
    private List<TablePraktikum> tablePraktikumList;
    private PraktikumDatabase praktikumDatabase;
    private PraktikumFragment praktikumFragment;

    public SoalPraktikumAdapter(Context context, List<TablePraktikum> tablePraktikumList, PraktikumDatabase praktikumDatabase, PraktikumFragment praktikumFragment) {
        this.context = context;
        this.tablePraktikumList = tablePraktikumList;
        this.praktikumDatabase = praktikumDatabase;
        this.praktikumFragment = praktikumFragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_soal_praktikum, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TablePraktikum data = tablePraktikumList.get(position);
        holder.tvNomor.setText((position +1)+".");
        holder.tvSoal.setText(data.getButir_soal());
        if (TextUtils.isEmpty(data.getJawabanUser())){
            holder.tvBtnCheck.setText("Cek");
            holder.edtJawaban.setEnabled(true);
            holder.btnCheck.setOnClickListener(v -> {
                if (!holder.edtJawaban.getText().toString().isEmpty()){
                    holder.edtJawaban.setError(null);
                    showPopUpHasil(data.getIdPraktikum(),holder.edtJawaban.getText().toString(),true);
                }else{
                    holder.edtJawaban.setError("Harap di isi");
                    holder.edtJawaban.requestFocus();
                }
            });
        }else{
            holder.tvBtnCheck.setText("Lihat");
            holder.edtJawaban.setText(data.getJawabanUser());
            holder.edtJawaban.setEnabled(false);
            holder.btnCheck.setOnClickListener(v -> showPopUpHasil(data.getIdPraktikum(),holder.edtJawaban.getText().toString(),false));
        }

    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    private void showPopUpHasil(int id, String jawabanUser, boolean insert) {
        if (insert){
            praktikumDatabase.praktikumDAO().updateJawabanPraktikumUser(jawabanUser,id);
        }
        TablePraktikum data = praktikumDatabase.praktikumDAO().getPraktikumById(id);
        final Dialog dialog = new Dialog(context,R.style.custom_dialog_theme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_praktikum_hasil);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);

        CardView cvHasil = dialog.findViewById(R.id.cv_hasil);
        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        LinearLayout lytSalah = dialog.findViewById(R.id.lyt_salah);
        EditText edtJawabanSalah = dialog.findViewById(R.id.edt_jawaban_salah);
        TextView tvBenar = dialog.findViewById(R.id.tv_benar);
        EditText edtJawabanBenar = dialog.findViewById(R.id.edt_jawaban_benar);
        if (data.getJawabanUser() != null && data.getJawabanUser().toLowerCase().equals(data.getJawaban().toLowerCase())){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cvHasil.setCardBackgroundColor(context.getColor(R.color.benar));
            }else{
                cvHasil.setCardBackgroundColor(R.color.benar);
            }
            tvTitle.setText("Jawaban Benar");
            lytSalah.setVisibility(View.GONE);
            tvBenar.setVisibility(View.GONE);
            edtJawabanBenar.setText(data.getJawaban());
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cvHasil.setCardBackgroundColor(context.getColor(R.color.salah));
            }else{
                cvHasil.setCardBackgroundColor(R.color.salah);
            }
            lytSalah.setVisibility(View.VISIBLE);
            tvBenar.setVisibility(View.VISIBLE);
            tvTitle.setText("Jawaban Salah");
            edtJawabanBenar.setText(data.getJawaban());
            edtJawabanSalah.setText(jawabanUser);
        }
        dialog.setOnDismissListener(dialog1 -> praktikumFragment.loadData());
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return (tablePraktikumList!=null)?tablePraktikumList.size():0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        EditText edtJawaban;
        CardView btnCheck;
        TextView tvNomor,tvSoal;
        TextView tvBtnCheck;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            edtJawaban = itemView.findViewById(R.id.edt_jawaban);
            btnCheck = itemView.findViewById(R.id.btn_check);
            tvNomor = itemView.findViewById(R.id.tv_nomor);
            tvSoal = itemView.findViewById(R.id.tv_butir_soal);
            tvBtnCheck = itemView.findViewById(R.id.tv_btn_check);
        }
    }
}
