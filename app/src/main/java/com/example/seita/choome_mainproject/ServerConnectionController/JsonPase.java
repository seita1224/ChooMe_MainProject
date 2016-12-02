package com.example.seita.choome_mainproject.ServerConnectionController;

import android.graphics.Bitmap;
import android.util.Log;

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
    private JSONObject jo = null;
    private JSONArray ja = null;
    private StringBuilder sb = null;

    //コンストラクタ
    //デフォルトコンストラクタ
    public JsonPase(){}

    //StringBilder
    public JsonPase(StringBuilder sb){this.sb = sb;}


    //ランキングの文字列を返すメソッド
    public ArrayList<Object> RankingJson(){
        //Jsonの情報保存用変数
        ArrayList<Object> jsonArray = new ArrayList<>();

        //Jsonデータの準備
        try {
            //Jsonデータを生成
            ja = new JSONArray(sb.toString());

            //JSONArrayを格納する
            for(int i = 0;i < ja.length();i++){
                jsonArray.add(ja.get(i)); //型の
            }
        } catch (JSONException e) {
            Log.e("C:JsonPase,M:Ranking...",e .toString());
        }
        return jsonArray;
    }

    //ユーザーデータを返すメソッド
    public String UserJson(){
        //Userデータの整形開始
        try {
            //JSONArrayの形に変える
            ja = new JSONArray(sb.toString());

            //Jsonデータ格納用にインスタンス生成
            sb = new StringBuilder();


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //商品のデータを返すメソッド
    public String GoodsJson(){
        //商品データ格納用StringBilder
        sb = new StringBuilder();

        return null;
    }

    //商品の写真を返すメソッド
    public Bitmap PictureData(){
        //商品写真格納用配列
        Bitmap bm[] = null;
        //Bitmap配列をJsonから生成し格納
        try {
            for (int i = 0;i < ja.length();i++){
                bm[i] = (Bitmap) ja.getJSONObject(i).get("picture");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //JSONObjectのgetter,setter
    public JSONObject getJo() {return jo;}
    public void setJo(JSONObject jo) {this.jo = jo;}

    //JSONArrayのgetter,setter
    public JSONArray getJa(){return ja;}
    public void setJa(JSONArray ja){this.ja = ja;}

    //StringBilderのgetter,setter
    public StringBuilder getSb() {return sb;}
    public void setSb(StringBuilder sb) {this.sb = sb;}

}
