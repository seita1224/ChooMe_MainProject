package com.example.seita.choome_mainproject;

/*
ランキング選択
 */
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;


public class RankingActivity extends AppCompatActivity {

    RadioGroup sexRadioGroup = null;

    Spinner ageSpinner = null;
    Spinner secenSpinner = null;
    Spinner genreSpinner = null;
    Spinner hobbiySpinner = null;

     static String age = "0",sex = "0",scene = "0",genre = "0",hobbie = "0",goodstype = "1";

    //ランキング判別フラグ群
    boolean sexFlg,ageFrag,hobbiesFlg,secenFlg,genreFlg;
    static boolean selectFlg = false;

    BootstrapButton serchButton = null;
    BootstrapButton receivePrezentButton = null;
    BootstrapButton wantPrezentButton = null;

    Bundle bundle = new Bundle();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_activity);

        sexRadioGroup = (RadioGroup)findViewById(R.id.sex_radio_group);
        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.raidiobutton_no  == checkedId){
                    sex = "0";
                    selectFlg = true;
                }else if(R.id.raidiobutton_man  == checkedId){
                    sex = "1";
                    selectFlg = true;
                }else{
                    sex = "2";
                    selectFlg = true;
                }
            }
        });
        sexRadioGroup.check(R.id.raidiobutton_no);

        ageSpinner = (Spinner)findViewById(R.id.Agespinner);
        secenSpinner = (Spinner)findViewById(R.id.Scenespinner);
        genreSpinner = (Spinner)findViewById(R.id.Genrespinner);
        hobbiySpinner = (Spinner)findViewById(R.id.Hobbiesspinner);

        ageSpinner.setFocusable(false);
        secenSpinner.setFocusable(false);
        genreSpinner.setFocusable(false);
        hobbiySpinner.setFocusable(false);

        ageSpinner.setOnItemSelectedListener(selectedListener);
        secenSpinner.setOnItemSelectedListener(selectedListener);
        genreSpinner.setOnItemSelectedListener(selectedListener);
        hobbiySpinner.setOnItemSelectedListener(selectedListener);

        serchButton = (BootstrapButton) findViewById(R.id.Searchbutton);
        receivePrezentButton = (BootstrapButton) findViewById(R.id.button_receive_prezent);
        wantPrezentButton = (BootstrapButton) findViewById(R.id.button_want_prezent);


        serchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectFlg == false){
                    Snackbar.make(findViewById(R.id.rankingactivity),"検索する項目を入力してください",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("sex",sex);
                intent.putExtra("age",age);
                intent.putExtra("scene",scene);
                intent.putExtra("genre",genre);
                intent.putExtra("hobbie",hobbie);
                intent.putExtra("goodstype",goodstype);
                intent.setClassName(getApplicationContext(),RankingResultActivity.class.getName());
                startActivity(intent);
            }
        });

        receivePrezentButton.setOnClickListener(clickListener);
        wantPrezentButton.setOnClickListener(clickListener);
    }


    private static AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner)parent;
            if(spinner.isFocusable() == false) {
                spinner.setFocusable(true);
            }else{
                Log.d("RankingActivity", String.valueOf(position));

                switch (spinner.getId()){
                    case R.id.Scenespinner:
                        scene = String.valueOf(position);
                        selectFlg = true;
                        break;
                    case R.id.Agespinner:
                        age = String.valueOf(position);
                        selectFlg = true;
                        break;
                    case R.id.Genrespinner:
                        genre = String.valueOf(position);
                        selectFlg = true;
                        break;
                    case R.id.Hobbiesspinner:
                        hobbie = String.valueOf(position);
                        selectFlg = true;
                        break;
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Log.d("RankingActivity", "Nothing");
        }
    };

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button_receive_prezent) {
                goodstype = "1";
                selectFlg = true;
                receivePrezentButton.setBootstrapBrand(DefaultBootstrapBrand.WARNING);
                wantPrezentButton.setBootstrapBrand(DefaultBootstrapBrand.REGULAR);
            } else {
                goodstype = "2";
                selectFlg = true;
                receivePrezentButton.setBootstrapBrand(DefaultBootstrapBrand.REGULAR);
                wantPrezentButton.setBootstrapBrand(DefaultBootstrapBrand.WARNING);
            }
        }
    };
}