package com.example.seita.choome_mainproject.maikeView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.seita.choome_mainproject.DBController.Goodsdata;
import com.example.seita.choome_mainproject.ProductReviewActivity;
import com.example.seita.choome_mainproject.R;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by yuki on 2016/05/26.
 */
public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.ViewHolder>{
        private String[] goodsList; //商品名リスト
        private String[] genresList; //商品のリストに対応するジャンル
        private Bitmap goodsImage; //商品のリストに対応する画像
        private Context context;
        double[] rate; //商品の評価

        ArrayList<Goodsdata> goodsdatas;//ランキング格納用ArrayList

        public CardRecyclerAdapter(Context context,String[] goodsList,String[] genresList,Bitmap goodsImage) {
                super();
                this.goodsList = goodsList;
                this.genresList = genresList;
                this.goodsImage = goodsImage;
                this.context = context;
        }

        //ランキングコンストラクタ
        public CardRecyclerAdapter(Context context, ArrayList<Goodsdata> goodsdatas){
                super();
                this.context = context;
                this.goodsdatas = goodsdatas;
        }

        @Override
        public int getItemCount() {
                return goodsList.length;
        }

        //ViewHolderにあるViewをカードビューにセットする
        @Override
        public void onBindViewHolder(ViewHolder vh, final int position) {
                //商品名の設定
                vh.mGoodsTextView.setText(goodsdatas.get(position).getGoods_name());

                //ジャンル名設定
                vh.mGenresTextView.setText(goodsdatas.get(position).getGenre());

                //商品画像の設定
                vh.mGoodsImageView.setImageBitmap(goodsdatas.get(position).getPicture());

                //カードビュー自体をクリックしたときの処理
                vh.mRankingResultRootLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Log.d("LayoutClick", String.valueOf(position) + " Rate ; " + goodsdatas.get(position).getRate());
                        }
                });

                //評価するための星を変えたときの処理
                vh.mRateRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                             rate[position] = v;
                        }
                });
                //星の数を商品のレートに初期化
            vh.mRateRatingBar.setRating(goodsdatas.get(position).getRate());

                final Bundle b = setBundle(vh);

                //レビューボタンをクリックしたときの処理
                vh.mProductReViewButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(context,ProductReviewActivity.class);  //レビュー画面に飛ばす
                                intent.putExtras(b);
                                context.startActivity(intent);
                        }
                });
        }


        @Override
        public CardRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View v = layoutInflater.inflate(R.layout.layout_recycler, parent, false);
                ViewHolder viewHolder = new ViewHolder(v);
                return viewHolder;
        }

        //今あるデータのBundleを生成
        public Bundle setBundle(ViewHolder vh){
                Bundle mBundle = new Bundle();
                mBundle.putString("goodsName", (String) vh.mGoodsTextView.getText());
                mBundle.putString("genres", (String) vh.mGenresTextView.getText());
                mBundle.putInt("rate", (int) vh.mRateRatingBar.getRating());
                Bitmap bmp = ((BitmapDrawable) vh.mGoodsImageView.getDrawable()).getBitmap();
                mBundle.putParcelable("goodsImage",bmp );
                return mBundle;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
                TextView mGoodsTextView;
                TextView mGenresTextView;
                LinearLayout mRankingResultRootLayout;
                ImageView mGoodsImageView;
                RatingBar mRateRatingBar;
                BootstrapButton mProductReViewButton;

                public ViewHolder(View v) {
                        super(v);
                        mGoodsTextView = (TextView)v.findViewById(R.id.goodsTextView);
                        mGenresTextView = (TextView)v.findViewById(R.id.genresTextView);
                        mRankingResultRootLayout = (LinearLayout)v.findViewById(R.id.rankingResultrootLayout);
                        mGoodsImageView = (ImageView)v.findViewById(R.id.goodsImageView);
                        mRateRatingBar = (RatingBar)v.findViewById(R.id.rateRaitingBar);
                        mProductReViewButton = (BootstrapButton)v.findViewById(R.id.productReviewButton);
                }
        }
}
