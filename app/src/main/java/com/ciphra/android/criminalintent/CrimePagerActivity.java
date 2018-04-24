package com.ciphra.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

/**
 * Created by jbarra on 4/24/2018.
 */

public class CrimePagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button firstButton;
    private Button lastButton;
    private static final String EXTRA_CRIME_ID = "com.ciphra.android.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                if(position == 0){
                    firstButton.setEnabled(false);
                    lastButton.setEnabled(true);
                }
                else if(position == (mCrimes.size()-1)){
                    firstButton.setEnabled(true);
                    lastButton.setEnabled(false);

                }

                else{
                    firstButton.setEnabled(true);
                    lastButton.setEnabled(true);
                }

                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });


        for (int i = 0; i < mCrimes.size(); ++i){
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        firstButton = (Button) findViewById(R.id.first_jump);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFirst();
            }
        });

        lastButton = (Button) findViewById(R.id.last_jump);
        lastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLast();
            }
        });
    }

    public void goToFirst(){
        mViewPager.setCurrentItem(0);
        firstButton.setEnabled(false);
    }

    public  void goToLast(){
        mViewPager.setCurrentItem(mCrimes.size()-1);
        lastButton.setEnabled(false);
    }
}
