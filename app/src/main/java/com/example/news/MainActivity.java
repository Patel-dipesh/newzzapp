package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {



    private String apikey = "5ea2477c9947400180671da174f0894f";
    TabLayout tabLayout;
    TabItem mhome,msports,mentertainment,mhealth,mscience;
    PagerAdaptor pagerAdaptor;
    Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        mhealth = findViewById(R.id.health);
        mhome = findViewById(R.id.home);
        msports = findViewById(R.id.sports);
        mentertainment = findViewById(R.id.entertainment);
        mscience = findViewById(R.id.science);
        ViewPager viewPager = findViewById(R.id.fragmentcontainer);
        tabLayout = findViewById(R.id.include);

        pagerAdaptor = new PagerAdaptor(getSupportFragmentManager(),5);
        viewPager.setAdapter(pagerAdaptor);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() >=0 && tab.getPosition()<=4)
                {
                    pagerAdaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}