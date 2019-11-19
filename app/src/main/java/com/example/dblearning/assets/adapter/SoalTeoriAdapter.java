package com.example.dblearning.assets.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dblearning.R;
import com.example.dblearning.assets.CustomGridView;
import com.example.dblearning.assets.PrefManager;
import com.example.dblearning.assets.database.JawabanTeoriDatabase;
import com.example.dblearning.assets.database.JawabanTeoriUserDatabase;
import com.example.dblearning.assets.database.TeoriDatabase;
import com.example.dblearning.assets.database.model.TableJawabanPraktikumUser;
import com.example.dblearning.assets.database.model.TableJawabanTeori;
import com.example.dblearning.assets.database.model.TableJawabanTeoriUser;
import com.example.dblearning.assets.database.model.TableTeori;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class SoalTeoriAdapter extends RecyclerView.Adapter<SoalTeoriAdapter.MyViewHolder> {
    private Context context;
    private List<TableTeori> tableTeoriList;
    private JawabanTeoriDatabase jawabanTeoriDatabase;
    private JawabanTeoriUserDatabase jawabanTeoriUserDatabase;
    private PrefManager prefManager;

    public SoalTeoriAdapter(Context context, List<TableTeori> tableTeoriList, JawabanTeoriDatabase jawabanTeoriDatabase, JawabanTeoriUserDatabase jawabanTeoriUserDatabase) {
        this.context = context;
        this.tableTeoriList = tableTeoriList;
        this.jawabanTeoriDatabase = jawabanTeoriDatabase;
        this.jawabanTeoriUserDatabase = jawabanTeoriUserDatabase;
        this.prefManager = new PrefManager(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_soal_teori, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TableTeori tableTeori = tableTeoriList.get(position);
        holder.tvNomor.setText((position +1)+".");
        holder.tvButirSoal.setText(tableTeori.getButir_soal());
        List<TableJawabanTeori> jawabanTeoriList = jawabanTeoriDatabase.jawabanTeoriDAO().getJawabanBySoal(tableTeori.getIdTeori());
        if (jawabanTeoriList!=null){
            ArrayList<String> dataJawab = new ArrayList<>();
            ArrayList<TableJawabanTeori> dataJawabTeori = new ArrayList<>();
            RadioGroup radioGroup = new RadioGroup(context);
            radioGroup.setOrientation(LinearLayout.VERTICAL);

            for (int i=0;i<jawabanTeoriList.size();i++){
                TableJawabanTeori tableJawabanTeori = jawabanTeoriList.get(i);
                dataJawab.add(tableJawabanTeori.getJawaban());
                dataJawabTeori.add(tableJawabanTeori);

                RadioButton rd = new RadioButton(context);
                rd.setId(tableJawabanTeori.getIdJawabanTeori());
                rd.setText(tableJawabanTeori.getJawaban());
                radioGroup.addView(rd);
            }
            holder.gridJawaban.removeAllViews();
            holder.gridJawaban.addView(radioGroup);
            //check save jawaban
            TableJawabanTeoriUser jawabanUserCheck = jawabanTeoriUserDatabase.jawabanTeoriUserDAO().getJawabanUser(tableTeori.getIdTeori());
            if (jawabanUserCheck!=null){
                radioGroup.check(jawabanUserCheck.getJawabanUser());
            }
            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                if (!prefManager.checkFInishLatihan()){
                    TableJawabanTeoriUser jawabanUser = jawabanTeoriUserDatabase.jawabanTeoriUserDAO().getJawabanUser(tableTeori.getIdTeori());
                    if (jawabanUser!=null){
                        Log.e(TAG, "jawaban user: "+jawabanUser.getJawabanUser() );
                        //update
                        jawabanTeoriUserDatabase.jawabanTeoriUserDAO().updateJawabanTeoriUser(checkedId,tableTeori.getIdTeori());
                    }else{
                        //simpan
                        jawabanTeoriUserDatabase.jawabanTeoriUserDAO().insertJawabanTeoriUser(new TableJawabanTeoriUser(tableTeori.getIdTeori(),checkedId));
                    }
                }else{
                    Toast.makeText(context, "Harus memulai lagi, untuk berlatih..", Toast.LENGTH_SHORT).show();
                }
            });
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
