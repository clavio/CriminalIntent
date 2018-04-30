package com.ciphra.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ciphra.android.criminalintent.database.CrimeDbSchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ciphra on 4/16/18.
 */

public class CrimeLab {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static CrimeLab sCrimeLab;
    private static HashMap<UUID, Crime> crimeDictonary;


    public static CrimeLab get(Context context) {

        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;

    }

        public void addCrime(Crime c){
            ContentValues values = getContentValues(c);
            mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null,values);
        }

        public void removeCrime (Crime c){
            crimeDictonary.remove(c.getId());
        }

    private CrimeLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
        crimeDictonary = new HashMap<>();
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME, values, CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    public List<Crime> getCrimes(){
        return new ArrayList<>();
    }

    public Crime getCrime(UUID id){
        return null;
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeDbSchema.CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeDbSchema.CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeDbSchema.CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        return values;
    }

    private Cursor queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null, //columns - null selects all columns
                whereClause,
                whereArgs,
                null, //group by
                null, //having
                null //order by
        );
        return cursor;
    }
}



