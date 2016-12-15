package com.example.seita.choome_mainproject.ServerConnectionController.ConnectionCallBacks.test;

import com.example.seita.choome_mainproject.ServerConnectionController.JsonParse.RankingJsonPase;

/**
 * Created by seita on 2016/12/02.
 */

public interface ConnectionCallBack {
    void receiveJson(RankingJsonPase rankingJsonPase);
}
