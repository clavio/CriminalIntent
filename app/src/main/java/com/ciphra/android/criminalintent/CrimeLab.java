package com.ciphra.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ciphra on 4/16/18.
 */

public class CrimeLab {

    private List<Crime> mCrimes;
    private static CrimeLab sCrimeLab;
    private static HashMap<UUID, Crime> crimeDictonary;


    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;

    }

        public void addCrime(Crime c){
        mCrimes.add(c);
        crimeDictonary.put(c.getId(), c);
        }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        crimeDictonary = new HashMap<>();
        for(int i = 0; i < 100; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i%2 ==0);

         //  crime.setRequiresPolice(i%3 ==0);
        }
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        return crimeDictonary.get(id);
    }
}



