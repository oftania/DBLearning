package com.example.dblearning.assets.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dblearning.quiz.PraktikumFragment;
import com.example.dblearning.quiz.TeoriFragment;

public class QuizTabsAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    public QuizTabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TeoriFragment();
            case 1:

                return new PraktikumFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
