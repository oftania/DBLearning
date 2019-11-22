package com.example.dblearning.assets.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dblearning.R;
import com.example.dblearning.assets.database.JawabanTeoriDatabase;
import com.example.dblearning.assets.database.JawabanTeoriUserDatabase;
import com.example.dblearning.assets.database.model.TableJawabanTeori;
import com.example.dblearning.assets.database.model.TableJawabanTeoriUser;
import com.example.dblearning.assets.database.model.TableTeori;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class KunciTeoriAdapter extends RecyclerView.Adapter<KunciTeoriAdapter.MyViewHolder> {
    private Context context;
    private List<TableTeori> tableTeoriList;
    private JawabanTeoriDatabase jawabanTeoriDatabase;
    private JawabanTeoriUserDatabase jawabanTeoriUserDatabase;

    public KunciTeoriAdapter(Context context, List<TableTeori> tableTeoriList, JawabanTeoriDatabase jawabanTeoriDatabase, JawabanTeoriUserDatabase jawabanTeoriUserDatabase) {
        this.context = context;
        this.tableTeoriList = tableTeoriList;
        this.jawabanTeoriDatabase = jawabanTeoriDatabase;
        this.jawabanTeoriUserDatabase = jawabanTeoriUserDatabase;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kunci_teori, parent, false));
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TableTeori tableTeori = tableTeoriList.get(position);
        holder.tvNomor.setText((position +1)+".");
        holder.tvButirSoal.setText(tableTeori.getButir_soal());
        List<TableJawabanTeori> jawabanTeoriList = jawabanTeoriDatabase.jawabanTeoriDAO().getJawabanBySoal(tableTeori.getIdTeori());
        if (jawabanTeoriList!=null){
            RadioGroup radioGroup = new RadioGroup(context);
            radioGroup.setOrientation(LinearLayout.VERTICAL);
            for (int i=0;i<jawabanTeoriList.size();i++){
                TableJawabanTeori tableJawabanTeori = jawabanTeoriList.get(i);

                RadioButton rd = new RadioButton(context);
                rd.setId(tableJawabanTeori.getIdJawabanTeori());
                rd.setEnabled(false);
                rd.setText(tableJawabanTeori.getJawaban());
                if (tableJawabanTeori.isStatusJawaban()){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        rd.setTextColor(context.getColor(R.color.salah));
                    }else{
                        rd.setTextColor(R.color.salah);
                    }
                }
                radioGroup.addView(rd);
            }
            holder.gridJawaban.removeAllViews();
            holder.gridJawaban.addView(radioGroup);
            //check save jawaban
            TableJawabanTeoriUser jawabanUserCheck = jawabanTeoriUserDatabase.jawabanTeoriUserDAO().getJawabanUser(tableTeori.getIdTeori());
            if (jawabanUserCheck!=null){
                radioGroup.check(jawabanUserCheck.getJawabanUser());
            }
        }
    }

    @Override
    public int getItemCount() {
        return (tableTeoriList!=null)?tableTeoriList.size():0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNomor,tvButirSoal;
        private LinearLayout gridJawaban;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomor = itemView.findViewById(R.id.tv_nomor);
            tvButirSoal = itemView.findViewById(R.id.tv_butir_soal);
            gridJawaban = itemView.findViewById(R.id.layout_jawaban);
        }
    }
}
