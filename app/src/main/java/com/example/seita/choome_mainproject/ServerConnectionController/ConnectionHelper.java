package com.example.seita.choome_mainproject.ServerConnectionController;

import android.util.Log;

import com.example.seita.choome_mainproject.DBController.*;

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
    private JSONObject json;
    private Userdata user;
    private Goodsdata goods;

    private ConnectionCallBack connectionCallBack;

    //JSONは帰ってくるが帰ってくるタイミングと合わせるのが難しい
    public JSONObject jore(){
        return json;
    }

    //-----------------------------受信-----------------------------
    //ユーザ情報受信
    public void receiveUserTask(){
        receive = new ReceiveJsonAsyncTask(url);
        receive.setCallBack(new AsyncCallBack() {
            @Override
            public void callBack(JSONObject jo) {
                json = jo;
            }
        });
        receive.execute();
        connectionCallBack.receiveJson();
    }

    //商品情報受信
    public void receiveGoods(){
        setUrl("");
        receive = new ReceiveJsonAsyncTask(url);
        receive.execute();
    }

    //ランキング情報の受信
    public void reciveRankingTask(){
        setUrl("");
        receive = new ReceiveJsonAsyncTask(url);
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
