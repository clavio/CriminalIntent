package com.ciphra.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Ciphra on 4/16/18.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
