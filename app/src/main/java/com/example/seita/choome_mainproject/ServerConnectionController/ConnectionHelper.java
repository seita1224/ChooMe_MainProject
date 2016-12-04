package com.example.seita.choome_mainproject.ServerConnectionController;

import android.util.Log;

import com.example.seita.choome_mainproject.DBController.*;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.AsyncCallBack;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.test.ConnectionCallBack;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by seita on 2016/10/31.
 */

//データを受信、送信できるよう処理をする中間クラス
public class ConnectionHelper {
    private SendJsonAsyncTask send = null;  //データ送信用の非同期処理クラス
    private ReceiveJsonAsyncTask receive = null;    //データ受信用の非同期処理クラス
    private URL url = null; //送受信先のURL
    private JsonPase jsonPase;
    private Userdata user;
    private Goodsdata goods;

    private ConnectionCallBack connectionCallBack;

    //-----------------------------受信-----------------------------
    //ユーザ情報受信
    public void receiveUserTask(){
        receive = new ReceiveJsonAsyncTask(url);
        receive.setCallBack(new AsyncCallBack() {
            @Override
            public void callBack(JSONObject jo) {
                connectionCallBack.receiveJson(jsonPase);
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
    public void reciveRankingTask(){
        receive = new ReceiveJsonAsyncTask(url);
        receive.setCallBack(new AsyncCallBack() {
            @Override
            public void callBack(JSONObject jo) {
                jsonPase = new JsonPase(jo);
                connectionCallBack.receiveJson(jsonPase);
                Log.d("ConnectionHelper","CallBack");
            }
        });
        receive.execute();
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
    public void setConnectionCallBack(ConnectionCallBack connectionCallBack){
        this.connectionCallBack = connectionCallBack;
    }
}
