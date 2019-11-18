package com.example.dblearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.dblearning.assets.CustomViewPager;
import com.example.dblearning.assets.adapter.QuizTabsAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity {
    private Context context;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        context = this;
        initComponent();
    }

    private void initComponent() {
        tabLayout.addTab(tabLayout.newTab().setText("Teori"));
        tabLayout.addTab(tabLayout.newTab().setText("Praktikum"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        QuizTabsAdapter tabsAdapter = new QuizTabsAdapter(getSupportFragmentManager(),2);
        viewPager.setAdapter(tabsAdapter);
        viewPager.setPagingEnabled(false);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
