package com.example.seita.choome_mainproject.ServerConnectionController;

import android.content.Context;
import android.util.Log;

import com.example.seita.choome_mainproject.DBController.*;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.AsyncCallBack;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.main.RankingReceive;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.main.UserReceive;
import com.example.seita.choome_mainproject.ServerConnectionController.JsonParse.RankingJsonPase;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by seita on 2016/10/31.
 */

//データを受信、送信できるよう処理をする中間クラス
public class ConnectionHelper {
    private SendJsonAsyncTask send = null;  //データ送信用の非同期処理クラス
    private ReceiveJsonAsyncTask receive = null;    //データ受信用の非同期処理クラス
    private URL url = null; //送受信先のURL
    private RankingJsonPase rankingJsonPase;
    private String connectionStatus;
    private int statusCode;
    private Context context;

    //ランキングに対応するArrayList
    ArrayList<Goodsdata> goodsdatas;

    //コールバック用変数
    private RankingReceive rankingReceive;
    private UserReceive userReceive;
    
    //-----------------------------コンストラクタ-----------------------------
    public ConnectionHelper(Context context){
        this.context = context;
    }
    
    
    //-----------------------------受信-----------------------------
    //ユーザ情報受信
    public void receiveUserTask(){
        receive = new ReceiveJsonAsyncTask(url);
        receive.setCallBack(new AsyncCallBack() {
            @Override
            public void asyncCallBack(JSONObject jo) {
                userReceive.receiveUser();
                Log.d("ConnectionHelper","CallBack");
            }
        });
        receive.execute();
    }

    //商品情報受信
    public void receiveGoods(){
        setUrl("");
        receive = new ReceiveJsonAsyncTask(url);
        receive.execute();
    }

    //ランキング情報の受信
    public void reciveRanking(){
        Log.d("ConnectionHelper","reciveRanking_");
        setUrl("http://choome.itsemi.net/api/1.0/ranking/?pattern=2&goodstype=1&key=pcdEhBroxNohtmKoek8iE34hQ6FZYbp");
        receive = new ReceiveJsonAsyncTask(url);
        receive.setCallBack(new AsyncCallBack() {
            @Override
            public void asyncCallBack(JSONObject jo) {
                checkError();
                RankingJsonPase jp = new RankingJsonPase(jo);
                goodsdatas = jp.getRanking();
                rankingReceive.rankReceive(goodsdatas,connectionStatus);
            }
        });

        receive.execute();
        Log.d("ConnectionHelper","通信処理");
    }

    //レビュー情報の受信
    public void receiveReview(){
        setUrl("");
        receive = new ReceiveJsonAsyncTask(url);
        receive.execute();
    }

    //-----------------------------送信-----------------------------
    //ユーザ情報送信
    public void sendUserTask(){
        setUrl("");
        send = new SendJsonAsyncTask();
        send.execute();
    }

    //商品情報送信
    public void sendGoods(){
        setUrl("");
        send = new SendJsonAsyncTask();
        send.execute();
    }

    //ランキング情報の送信
    public void sendRankingTask(){
        setUrl("");
        send = new SendJsonAsyncTask();
        send.execute();
    }

    //送受信用URLにURLをセット
    public void setUrl(String str){
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            Log.e("ConnectionHelper",e.toString());
        }
    }

    //コールバック処理セットメソッド
    //ランキング
    public void setConnectionCallBack(RankingReceive rankingReceive){this.rankingReceive = rankingReceive;}
    //ユーザー
    public void setConnectionCallBack(UserReceive userReceive){this.userReceive = userReceive;}

    //エラー処理用メソッド
    public void checkError(){
        statusCode = receive.getStatusCode();
        //エラーコードが設定されている場合
        switch (receive.getStatusCode()){
            case 001:
                connectionStatus = receive.getConnectionStatus();
                Log.d("ConnectionHelper",connectionStatus);
                break;
            case 002:
                connectionStatus = receive.getConnectionStatus();
                Log.e("ConnectionHelper",connectionStatus);
                return;
            case 003:
                connectionStatus = receive.getConnectionStatus();
                Log.e("ConnectionHelper",connectionStatus);
                return;
        }
    }
}