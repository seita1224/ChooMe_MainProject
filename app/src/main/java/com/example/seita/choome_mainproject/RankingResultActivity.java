package com.example.seita.choome_mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.test.ConnectionCallBack;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionHelper;
import com.example.seita.choome_mainproject.ServerConnectionController.JsonPase;
import com.example.seita.choome_mainproject.maikeView.CardRecyclerView;

public class RankingResultActivity extends AppCompatActivity {

    CardRecyclerView cardRecyclerView = null;

    ConnectionHelper connectionHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_activity);

        //CardRecyclerViewのオブジェクトの取得
        cardRecyclerView = (CardRecyclerView)findViewById(R.id.rankingRecyclerView);
        //アダプターの生成＆セット
//        CardRecyclerAdapter adapter = new CardRecyclerAdapter();
//        cardRecyclerView.setRecyclerAdapter();

        ConnectionCallBack connectionCallBack = new ConnectionCallBack() {
            @Override
            public void receiveJson(JsonPase jsonPase) {

            }
        };
        connectionHelper.setConnectionCallBack(connectionCallBack);
    }

    //検索のデータを受け取る処理(仮)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
