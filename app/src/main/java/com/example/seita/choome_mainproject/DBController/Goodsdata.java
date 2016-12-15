package com.example.seita.choome_mainproject.DBController;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.util.Log;

import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.main.ImageReceive;
import com.example.seita.choome_mainproject.ServerConnectionController.ReceiveImageAsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by seita_v on 2016/10/21.
 */

//商品情報を扱うクラス
public class Goodsdata{
    //フィールド
    private String goods_name;//商品の名前
    private int goods_id;//商品ID
    private double rate;//評価
    private Bitmap picture;//画像
    private String comment;//コメント
    private String genre;//ジャンル
    private String scene;//シーン
    private String hobbies;//趣味

    //コンストラクタ
    public Goodsdata(){}

    //goods_name
    public String getGoods_name() {return goods_name;}
    public void setGoods_name(String goods_name) {this.goods_name = goods_name;}

    //goods_id
    public int getGoods_id() {return goods_id;}
    public void setGoods_id(int goods_id) {this.goods_id = goods_id;}

    //rate
    public double getRate() {return rate;}
    public void setRate(double rate) {this.rate = rate;}

    //picture
    public Bitmap getPicture() {return picture;}
    public void setPicture(Bitmap picture) {this.picture = picture;}

    //comment
    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}

    //genre
    public String getGenre() {return genre;}
    public void setGenre(String genre) {this.genre = genre;}

    //scene
    public String getScene() {return scene;}
    public void setScene(String scene) {this.scene = scene;}

    //getHobby
    public String getHobbies() {return hobbies;}
    public void setHobbies(String hobbies) {this.hobbies = hobbies;}

    //イメージファイルのURLをセットしダウンロードしてくる
    public void setImage(URL url){
        Log.d("Goodsdata","画像データの取得");
        ReceiveImageAsyncTask receiveImageAsyncTask = new ReceiveImageAsyncTask(url);
        receiveImageAsyncTask.setImageReceive(new ImageReceive() {
            @Override
            public void receiveImage(Bitmap bm) {
                picture = bm;
            }
        });
        receiveImageAsyncTask.execute();
    }


//    public void paseData() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(goods_id);
//        sb.append(goods_name);
//        sb.append(valuation);
//        //sb.append(picture);
//        sb.append(comment);
//        sb.append(genre);
//        sb.append(scene);
//        sb.append(hobbies);
//    }
}

