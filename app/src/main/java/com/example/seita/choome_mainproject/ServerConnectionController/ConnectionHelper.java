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
    private String connectionStatus;
    private int statusCode;
    private Context context;

    //URL生成用
    final private String DOMEIN = "http://choome.itsemi.net/";
    final private String API_VERSION = "api/1.0/";
    final private String API_CODE = "&key=pcdEhBroxNohtmKoek8iE34hQ6FZYbp";

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
        receive = new ReceiveJsonAsyncTask(url);
        receive.execute();
    }

    //ランキング情報の受信
    public void reciveRanking(String age,String sex,String scene,String genre,String hobbie,String goodstype){
        Log.d("ConnectionHelper","reciveRanking_");

        setUrl("ranking/?",age,sex,scene,genre,hobbie,goodstype);

        receive = new ReceiveJsonAsyncTask(url);
        receive.setCallBack(new AsyncCallBack() {
            @Override
            public void asyncCallBack(JSONObject jo) {
                if (checkError()) {
                    RankingJsonPase jp = new RankingJsonPase(jo);
                    goodsdatas = jp.getRanking();
//                    rankingReceive.rankReceive(goodsdatas, connectionStatus);
                }else{
                    Log.d("ConnectuonHelper","通信処理に失敗しました");
                }
            }
        });
        receive.execute();
        Log.d("ConnectionHelper","通信処理");
    }

    //レビュー情報の受信
    public void receiveReview(){
        receive = new ReceiveJsonAsyncTask(url);
        receive.execute();
    }

    //-----------------------------送信-----------------------------
    //ユーザ情報送信
    public void sendUserTask(){
        send = new SendJsonAsyncTask();
        send.execute();
    }

    //商品情報送信
    public void sendGoods(){
        send = new SendJsonAsyncTask();
        send.execute();
    }

    //ランキング情報の送信
    public void sendRankingTask(){
        send = new SendJsonAsyncTask();
        send.execute();
    }

    //送受信用URLにURLをセット
    public void setUrl(String informationType,String age,String sex,String scene,String genre,String hobbie,String goodstype){
        try {
            url = new URL(DOMEIN +
                    API_VERSION +
                    informationType +
                    "age=" + age +
                    "&sex=" + sex +
                    "&scene=" + scene +
                    "&genre=" + genre +
                    "&hobbie=" + hobbie +
                    "&goodstype=" + goodstype+
                    API_CODE);

            Log.d("URL",DOMEIN +
                    API_VERSION +
                    informationType +
                    "age=" + age +
                    "&sex=" + sex +
                    "&scene=" + scene +
                    "&genre=" + genre +
                    "&hobbie=" + hobbie +
                    "&goodstype=" + goodstype+
                    API_CODE);
        } catch (MalformedURLException e) {
            Log.e("ConnectionHelper",e.toString());
        }
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
    public boolean checkError(){
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
                return false;
            case 003:
                connectionStatus = receive.getConnectionStatus();
                Log.e("ConnectionHelper",connectionStatus);
                return false;
        }
        return true;
    }
}