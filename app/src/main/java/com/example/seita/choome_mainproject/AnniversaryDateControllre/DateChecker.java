package com.example.seita.choome_mainproject.AnniversaryDateControllre;

import android.util.Log;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by seita on 2017/01/23.
 */

public class DateChecker {
    public static int flg;

    public static int checkDate(ArrayList<String> list){

        long pastDays = 100;

        for(String str:list){
            String date = str.split(",")[0];

            Log.d("DateChecker","date:" + date);

            Calendar nowDate = Calendar.getInstance();
            nowDate.set(nowDate.get(Calendar.YEAR), nowDate.get(Calendar.MONTH) + 1, nowDate.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

            Log.d("DateChecker","nowdate:" + nowDate.get(Calendar.YEAR) +  nowDate.get(Calendar.MONTH) + 1 + nowDate.get(Calendar.DAY_OF_MONTH));

            Calendar anniversaryDate = Calendar.getInstance();
            anniversaryDate.set(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(4,6)),Integer.parseInt(date.substring(6,8)),0,0,0);

            Log.d("DateChecker","anniversaryDate:" + date.substring(0,4) + date.substring(4,6) + date.substring(6,8));

            long diffTime = nowDate.getTimeInMillis() - anniversaryDate.getTimeInMillis();

            Log.d("DateChecker","diffTime:" + diffTime);

            if (diffTime >= 0){
                if(pastDays > (diffTime / (1000 * 60 * 60 * 24))){
                    pastDays = diffTime / (1000 * 60 * 60 * 24);
                    Log.d("DateChecker","pastDays:" + pastDays);
                }
            }
        }
        Log.d("DateChecker","date:" + pastDays);
        return ((int) pastDays);
    }
}
