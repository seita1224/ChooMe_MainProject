package com.example.seita.choome_mainproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;

import static android.content.Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY;

public class ProductReviewActivity extends AppCompatActivity {

    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_review);
        Intent intent = getIntent();
        mText = (TextView)findViewById(R.id.infomationTextView);
        String str = intent.getStringExtra("goodsName");
        mText.setText(str);
    }
}
