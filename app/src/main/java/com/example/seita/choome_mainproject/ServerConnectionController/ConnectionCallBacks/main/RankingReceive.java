package com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.main;

import com.example.seita.choome_mainproject.DBController.Goodsdata;

import java.util.ArrayList;

/**
 * Created by seita on 2016/12/05.
 */

public interface RankingReceive {
    public void rankReceive(ArrayList<Goodsdata> goodsdatas,String connectionStatus);
}
