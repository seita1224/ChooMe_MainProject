package com.example.seita.choome_mainproject.maikeView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.example.seita.choome_mainproject.DBController.Goodsdata;
import com.example.seita.choome_mainproject.R;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.main.RankingReceive;
import com.example.seita.choome_mainproject.ServerConnectionController.ConnectionHelper;

import java.util.ArrayList;

/**
 * Created by yuki on 2016/05/26.
 */
public class CardRecyclerView extends RecyclerView{
    public CardRecyclerView(Context context){
        super(context);
    }
    public CardRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //ランキング用ArrayList
    ArrayList<Goodsdata> goodsList = null;

    public void setRecyclerAdapter(Context context,ArrayList<Goodsdata> goodsdatas){
        //カードビューの生成についてのログ
        Log.d("CardRecyclerView","カードビューの生成");
        Log.d("CardRecyclerView", String.valueOf(goodsdatas.size()));
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(new CardRecyclerAdapter(context,goodsdatas));
    }

}
