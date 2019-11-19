package com.example.dblearning.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.Room;

import com.example.dblearning.R;
import com.example.dblearning.assets.AppData;
import com.example.dblearning.assets.database.JawabanTeoriDatabase;
import com.example.dblearning.assets.database.JawabanTeoriUserDatabase;
import com.example.dblearning.assets.database.TeoriDatabase;
import com.example.dblearning.assets.database.model.TableJawabanTeori;
import com.example.dblearning.assets.database.model.TableJawabanTeoriUser;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LihatHasilActivity extends AppCompatActivity {
    private Context context;
    @BindView(R.id.tv_nilai)
    TextView tvNilai;
    @BindView(R.id.chart)
    ColorfulRingProgressView chart;
    @BindView(R.id.tv_benar)
    TextView tvBenar;
    @BindView(R.id.tv_salah)
    TextView tvSalah;
    @BindView(R.id.btn_lihat_key)
    CardView btnLihatKey;
    @BindView(R.id.btn_kembali)
    CardView btnKembali;
    private TeoriDatabase teoriDatabase;
    private JawabanTeoriDatabase jawabanTeoriDatabase;
    private JawabanTeoriUserDatabase jawabanTeoriUserDatabase;
    private int benar=0,salah=0,totalsoal=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_hasil);
        ButterKnife.bind(this);
        context = this;
        initDatabase();
        hitungNilai();

    }

    private void hitungNilai() {
        totalsoal = jawabanTeoriUserDatabase.jawabanTeoriUserDAO().getTotalJawabanTeoriUser();
        List<TableJawabanTeoriUser> tempJawabanTeori = jawabanTeoriUserDatabase.jawabanTeoriUserDAO().getAllJawabanUser();
        for (int i=0;i<tempJawabanTeori.size();i++){
            TableJawabanTeori jawabanTeori = jawabanTeoriDatabase.jawabanTeoriDAO().getJawabanByidSoaldanJawaban(tempJawabanTeori.get(i).getSoalId(),tempJawabanTeori.get(i).getJawabanUser());
            if (jawabanTeori.isStatusJawaban()){
                //benar
                benar ++;
            }else{
//                salajh
                salah ++;
            }
        }
        tvNilai.setText(""+benar);
        tvBenar.setText(benar+" Soal");
        tvSalah.setText(salah+" Soal");
        chart.setPercent((100/totalsoal)*benar);
    }

    private void initDatabase() {
        teoriDatabase = Room.databaseBuilder(context, TeoriDatabase.class, AppData.DATABASE_TEORI)
                .allowMainThreadQueries()
                .build();
        jawabanTeoriDatabase = Room.databaseBuilder(context, JawabanTeoriDatabase.class,AppData.DATAVASE_JAWABAN_TEORI)
                .allowMainThreadQueries()
                .build();
        jawabanTeoriUserDatabase = Room.databaseBuilder(context, JawabanTeoriUserDatabase.class,AppData.DATABASE_JAWABAN_TEORI_USER)
                .allowMainThreadQueries()
                .build();
    }

    @OnClick({R.id.btn_lihat_key, R.id.btn_kembali})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_lihat_key:
                startActivity(new Intent(context,KunciJawabanActivity.class));
                break;
            case R.id.btn_kembali:
                finish();
                break;
        }
    }
}
