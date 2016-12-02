package com.example.seita.choome_mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProductReviewActivity extends AppCompatActivity {

    TextView mText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_review);

        mText = (TextView)findViewById(R.id.infomationTextView);
        Intent intent = getIntent();
        String str = intent.getStringExtra("goodsName");
        mText.setText(str);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
