package com.example.dblearning.assets.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dblearning.Informasi;
import com.example.dblearning.informasi.BantuanFragment;
import com.example.dblearning.informasi.InformasiFragment;
import com.example.dblearning.quiz.PraktikumFragment;
import com.example.dblearning.quiz.TeoriFragment;

public class InformasiTabsAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    public InformasiTabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InformasiFragment();
            case 1:

                return new BantuanFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
