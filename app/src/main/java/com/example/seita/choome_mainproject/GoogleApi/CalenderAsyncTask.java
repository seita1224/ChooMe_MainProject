package com.example.seita.choome_mainproject.GoogleApi;

/**
 * Created by seita on 2017/01/13.
 */


import android.os.AsyncTask;

import com.example.seita.choome_mainproject.GoogleApi.ApiCallBack.CancelledCallBack;
import com.example.seita.choome_mainproject.GoogleApi.ApiCallBack.OnPostExecuteCallBack;
import com.example.seita.choome_mainproject.GoogleApi.ApiCallBack.OnPreExecuteCallBack;
import com.example.seita.choome_mainproject.HomeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;

import java.io.IOException;

/**
 * 非同期で　Google Calendar API の呼び出しを行うクラス。
 */
public class CalenderAsyncTask extends AsyncTask<Object, Object, String> {

    private com.google.api.services.calendar.Calendar mService = null;
    private Exception mLastError = null;

    //Cancell,onPostExecute,onPreExecute処理をコールバックする変数
    CancelledCallBack cancelledCallBack;
    OnPostExecuteCallBack onPostExecuteCallBack;
    OnPreExecuteCallBack onPreExecuteCallBack;

    public CalenderAsyncTask(){}

    public CalenderAsyncTask(GoogleAccountCredential credential) {
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        mService = new com.google.api.services.calendar.Calendar
                .Builder(transport, jsonFactory, credential)
                .setApplicationName("Google Calendar API Android Quickstart")
                .build();
    }

    /**
     * Google Calendar API を呼び出すバックグラウンド処理。
     *
     * @param params 引数は不要
     */
    @Override
    protected String doInBackground(Object... params) {
        try {
            return createCalendar();
        } catch (Exception e) {
            mLastError = e;
            cancel(true);
            return null;
        }
    }

    private String createCalendar() throws IOException {
        // 新規にカレンダーを作成する
        com.google.api.services.calendar.model.Calendar calendar = new Calendar();
        // カレンダーにタイトルを設定する
        calendar.setSummary("AnniversaryCalendar");
        // カレンダーにタイムゾーンを設定する
        calendar.setTimeZone("Asia/Tokyo");

        // 作成したカレンダーをGoogleカレンダーに追加する
        Calendar createdCalendar = mService.calendars().insert(calendar).execute();
        String calendarId = createdCalendar.getId();

        // カレンダー一覧から新規に作成したカレンダーのエントリを取得する
        CalendarListEntry calendarListEntry = mService.calendarList().get(calendarId).execute();

        // カレンダーのデフォルトの背景色を設定する
        calendarListEntry.setBackgroundColor("#ff0000");

        // カレンダーのデフォルトの背景色をGoogleカレンダーに反映させる
        CalendarListEntry updatedCalendarListEntry =
                mService.calendarList()
                        .update(calendarListEntry.getId(), calendarListEntry)
                        .setColorRgbFormat(true)
                        .execute();

        // 新規に作成したカレンダーのIDを返却する
        return calendarId;
    }


    @Override
    protected void onPreExecute() {
        onPreExecuteCallBack.onPreExecuteCallBack();
    }

    @Override
    protected void onPostExecute(String output) {
        onPostExecuteCallBack.onPostExecute(output);
    }

    @Override
    protected void onCancelled() {
        cancelledCallBack.cancelledCallBack(mLastError);
    }

    //コールバック処理セットメソッド
    public void setCancellCallBack(CancelledCallBack cl){cancelledCallBack = cl;}
    public void setOnPostExecute(OnPostExecuteCallBack oPost){onPostExecuteCallBack = oPost;}
    public void setOnPreExecuteCallBack(OnPreExecuteCallBack oPre){onPreExecuteCallBack = oPre;}

    //GoogleAPIを使用するためのクラス生成、初期化
    public void setGoogleAccountCredential(GoogleAccountCredential credential){
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        mService = new com.google.api.services.calendar.Calendar
                .Builder(transport, jsonFactory, credential)
                .setApplicationName("Google Calendar API Android Quickstart")
                .build();
    }

}