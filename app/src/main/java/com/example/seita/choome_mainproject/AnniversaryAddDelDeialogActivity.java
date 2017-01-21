package com.example.seita.choome_mainproject;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.seita.choome_mainproject.R;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.GregorianCalendar;

public class AnniversaryAddDelDeialogActivity extends AppCompatActivity{

    public static final String LOCAL_FILE = "AnniversaryDate.txt";
    DatePicker mDatePicker = null;
    TextInputEditText mAnniversaryEditText = null;
    BootstrapButton mAddButton = null;
    BootstrapButton mCancelButton = null;

    //記念日の登録年月日
    GregorianCalendar date = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anniversary_add_del_deialog);

        mDatePicker = (DatePicker)findViewById(R.id.datePicker);
        mAnniversaryEditText = (TextInputEditText)findViewById(R.id.AnniversaryEditText);
        mAddButton = (BootstrapButton)findViewById(R.id.AddButton);
        mCancelButton = (BootstrapButton)findViewById(R.id.CancelButton);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(mAnniversaryEditText.getText()).equals("")){
                    Toast.makeText(getApplicationContext(),"記念名を入力してください",Toast.LENGTH_SHORT).show();
                    return;
                }else if(String.valueOf(mAnniversaryEditText.getText()).startsWith("[") | String.valueOf(mAnniversaryEditText.getText()).startsWith("]")){
                    Toast.makeText(getApplicationContext(),"申し訳ございません「[」又は「]」は使用できません",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mDatePicker.getYear() == 0){
                    Toast.makeText(getApplicationContext(),"記念日を入力してください",Toast.LENGTH_SHORT).show();
                    return;
                }
                writeFile("[" + mAnniversaryEditText.getText() + "]" + "[" + mDatePicker.getYear() + mDatePicker.getMonth()+1 + mDatePicker.getDayOfMonth() + "]");
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void writeFile(String data){
        BufferedWriter writer;
        Log.d("AnniversaryAddDel...",data);
        try {
            writer = new BufferedWriter(new OutputStreamWriter(openFileOutput(LOCAL_FILE, MODE_PRIVATE|MODE_APPEND), "UTF-8"));
            //追記する
            if(data != null) {
                writer.append(data);
                writer.newLine();
                writer.close();
            }else{
                Toast.makeText(getApplicationContext(),"記念日を入力してください",Toast.LENGTH_SHORT);
            }
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
        finish();
    }
}
