package com.example.seita.choome_mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.seita.choome_mainproject.DBController.Goodsdata;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.main.RankingReceive;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionHelper;
import com.example.seita.choome_mainproject.maikeView.CardRecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class RankingResultActivity extends AppCompatActivity {

    CardRecyclerView cardRecyclerView = null;
    LinearLayout layout = null;

    ConnectionHelper connectionHelper = null;

    ArrayList<Goodsdata> goodsList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_activity);

        Log.d("RankingResultActivity","Load RankingActivity");

        //ランキングアクティビティのレイアウトを保存
        layout = new LinearLayout(this);

        //カードレイアウトの準備
        cardRecyclerView = new CardRecyclerView(getApplicationContext());

        //サーバからデータのダウンロードの準備
        connectionHelper = new ConnectionHelper(getApplicationContext());
        //データのダウンロード&ArrayListに格納&カードビューにセットの処理を
        connectionHelper.setConnectionCallBack(new RankingReceive() {
            @Override
            public void rankReceive(ArrayList<Goodsdata> goodsdatas,String connectionStatus) {
                Log.d("RankingResultActivity","Load CardView");
                Log.d("RankingResultActivity", String.valueOf(goodsdatas.size()));
                cardRecyclerView.setRecyclerAdapter(getApplicationContext(),goodsdatas);
                setContentView(cardRecyclerView);
            }
        });
        //ランキングデータ受信
        connectionHelper.reciveRanking();
    }

    //検索のデータを受け取る処理(仮)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
