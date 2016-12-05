package com.example.seita.choome_mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.seita.choome_mainproject.DBController.Goodsdata;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.main.RankingReceive;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.test.ConnectionCallBack;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionHelper;
import com.example.seita.choome_mainproject.ServerConnectionController.JsonPase;
import com.example.seita.choome_mainproject.maikeView.CardRecyclerAdapter;
import com.example.seita.choome_mainproject.maikeView.CardRecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class RankingResultActivity extends AppCompatActivity {

    CardRecyclerView cardRecyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_activity);
    }

    //検索のデータを受け取る処理(仮)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
