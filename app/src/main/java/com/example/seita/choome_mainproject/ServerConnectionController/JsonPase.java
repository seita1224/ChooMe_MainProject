package com.example.seita.choome_mainproject.ServerConnectionController;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.seita.choome_mainproject.DBController.Goodsdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by seita on 2016/10/24.
 */

//Jsonデータをパース(解析)するクラス
public class JsonPase {
    //フィールド
    private JSONObject rootJsonObject = null;
    private JSONArray rootJsonArray = null;

    //コンストラクタ
    //デフォルトコンストラクタ
    public JsonPase(){}

    //JSONObject
    public JsonPase(JSONObject jo){this.rootJsonObject = jo;}

    //JSONArray
    public JsonPase(JSONArray ja){this.rootJsonArray = ja;}

    //ランキングの文字列を返すメソッド
    public ArrayList<Goodsdata> RankingJson(){
        //ランキング格納用ArrayList
        ArrayList<Goodsdata> rankList = new ArrayList<>();

        //一時退避用JSONObject
        JSONObject tempJo;

        //JSONから順位だけを抜き出す
        for (int i = 0;i < 20;i++){
            try {
                //1～20位まで一つずつ退避させる
                tempJo = rootJsonObject.getJSONObject(String.valueOf(i + 1));

                //GoodsdataClassに一つずつ入れる
                Goodsdata data = new Goodsdata();

                data.setGoods_id(tempJo.getInt("getgoods_id"));

                //GoodsdataをrankListに追加
                rankList.add(data);
            } catch (JSONException e) {
                Log.e("JsonPase","ランキングデータの取得に失敗しました");
            }
        }
        return rankList;
    }

    //ユーザーデータを返すメソッド
    public String UserJson(){
        return null;
    }

    //商品のデータを返すメソッド
    public String GoodsJson(){
        return null;
    }

    //商品の写真を返すメソッド
    public Bitmap PictureData(){
        return null;
    }

    //JSONObjectのgetter,setter
    public JSONObject getJo() {return rootJsonObject;}
    public void setJo(JSONObject jo) {this.rootJsonObject = jo;}

    //JSONArrayのgetter,setter
    public JSONArray getJa(){return rootJsonArray;}
    public void setJa(JSONArray ja){this.rootJsonArray= ja;}
}
