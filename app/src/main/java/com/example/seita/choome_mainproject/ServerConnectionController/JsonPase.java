package com.example.seita.choome_mainproject.ServerConnectionController;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

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

    //JSONのデータを判別するためのメソッド
    public boolean serectInfo(String type) {
        Log.d("JsonParse","serectInfo");
        try {
            //ここにデータ種別が増えたときcase文追加
           if(type == rootJsonObject.getString("Type")){
               return true;
           }else{
               return false;
           }
        } catch (JSONException e) {
            Log.e("JsonPase", e.toString());
        }
        return false;
    }


    //ランキングの文字列を返すメソッド
    public ArrayList<Goodsdata> getRanking(){
        //ランキング格納用ArrayList
        ArrayList<Goodsdata> rankList = new ArrayList<>();

        //一時退避用JSONObject,JSONArray
        JSONArray tempJa;
        JSONObject tempJo;

        try {

            //取得するデータがあっているかどうか判定
            serectInfo(rootJsonObject.getString("Type"));

            //JSONArrayにランキング配列を入れる
            tempJa = rootJsonObject.getJSONArray("Items");

            //JSONから順位だけを抜き出す
            for (int i = 0;i < 20;i++){
                tempJo = tempJa.getJSONObject(i).getJSONObject("Item");

                //データの確認
                Log.d("JsonParse", tempJa.getJSONObject(i).getJSONObject("Item").toString());

                //GoodsdataClassに一つずつ入れる
                Goodsdata data = new Goodsdata();

                //データを一つずつ格納する
                data.setGoods_id(tempJo.getInt("getgoods_id"));
                data.setGoods_name(tempJo.getString("name"));
                data.setGenre(tempJo.getString("genres"));
                data.setRate(tempJo.getInt("average_rate"));

                //GoodsdataをrankListに追加
                rankList.add(data);
            }
        } catch (JSONException e) {
            Log.e("JsonPase","ランキングデータの生成に失敗しました : " + e.toString());
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
