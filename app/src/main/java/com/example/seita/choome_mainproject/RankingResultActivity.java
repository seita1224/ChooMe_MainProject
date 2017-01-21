package com.example.seita.choome_mainproject;

/*
ランキング結果表示
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.seita.choome_mainproject.DBController.Goodsdata;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.main.RankingReceive;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionHelper;
import com.example.seita.choome_mainproject.maikeView.CardRecyclerView;

import java.util.ArrayList;

public class RankingResultActivity extends AppCompatActivity {

    CardRecyclerView cardRecyclerView = null;
    LinearLayout layout = null;

    ConnectionHelper connectionHelper = null;

    ArrayList<Goodsdata> goodsList = null;

    String age,sex,scene,genre,hobbie,goodstype;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_result);

        Log.d("RankingResultActivity","Load RankingActivity");

        //ランキングアクティビティのレイアウトを保存
        layout = new LinearLayout(this);

        //ランキング選択用変数取得
        intent = getIntent();
        sex = intent.getStringExtra("sex");
        age = intent.getStringExtra("age");
        scene = intent.getStringExtra("scene");
        genre = intent.getStringExtra("genre");
        hobbie = intent.getStringExtra("hobbie");
        goodstype = intent.getStringExtra("goodstype");

//        sex = "1";
//        age = "3";
//        goodstype = "1";

        //カードレイアウトの準備
        cardRecyclerView = new CardRecyclerView(getApplicationContext());

        //サーバからデータのダウンロードの準備
        connectionHelper = new ConnectionHelper(getApplicationContext());

        //データのダウンロード&ArrayListに格納&カードビューにセットの処理を
        connectionHelper.setConnectionCallBack(new RankingReceive() {
            @Override
            public void rankReceive(ArrayList<Goodsdata> goodsdatas,String connectionStatus) {
                Log.d("RankingResultActivity","Load CardView");
                cardRecyclerView.setRankingRecyclerAdapter(getApplicationContext(),goodsdatas);
                cardRecyclerView.setBackgroundResource(R.drawable.haikeiii);
                setContentView(cardRecyclerView);
            }
        });
        //ランキングデータ受信
        connectionHelper.reciveRanking(age,sex,scene,genre,hobbie,goodstype);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        connectionHelper = null;
        cardRecyclerView = null;
    }
}
